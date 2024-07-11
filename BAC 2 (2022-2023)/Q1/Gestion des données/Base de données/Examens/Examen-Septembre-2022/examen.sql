DROP SCHEMA IF EXISTS examen CASCADE;

CREATE SCHEMA examen;

CREATE TABLE examen.articles (
    id_article SERIAL PRIMARY KEY,
    nom VARCHAR NOT NULL CHECK ( nom <> '' ),
    prix INTEGER NOT NULL CHECK ( prix > 0 ),
    poids INTEGER NOT NULL CHECK ( poids > 0 ),
    quantite_max INTEGER DEFAULT NULL CHECK ( quantite_max > 0 )
);

CREATE TABLE examen.commandes (
    id_commande SERIAL PRIMARY KEY,
    nom_client VARCHAR NOT NULL CHECK ( nom_client <> '' ),
    date_commande DATE NOT NULL DEFAULT now(),
    type_commande VARCHAR NOT NULL CHECK (type_commande IN ('livraison', 'à emporter')),
    poids_total INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE examen.lignes_de_commande (
    id_commande INTEGER NOT NULL REFERENCES examen.commandes(id_commande),
    id_article INTEGER NOT NULL REFERENCES examen.articles(id_article),
    quantite INTEGER NOT NULL CHECK ( quantite > 0 ),
    PRIMARY KEY (id_commande, id_article)
);

CREATE OR REPLACE FUNCTION examen.ajouter_article (_id_commande INTEGER, _id_article INTEGER) RETURNS INTEGER AS $$
DECLARE
    _nbr_articles INTEGER;
BEGIN
    IF EXISTS (SELECT * FROM examen.lignes_de_commande WHERE id_commande = _id_commande AND id_article = _id_article) THEN
        UPDATE examen.lignes_de_commande SET quantite = quantite + 1 WHERE id_commande = _id_commande AND id_article = _id_article;
    ELSE
        INSERT INTO examen.lignes_de_commande (id_commande, id_article, quantite) VALUES (_id_commande, _id_article, 1);
    END IF;
    -- Retourne le nombre d'articles qui ont été commandés dans au moins deux commandes différentes
    SELECT COUNT(DISTINCT id_article) INTO _nbr_articles FROM examen.lignes_de_commande lc1, examen.lignes_de_commande lc2 WHERE lc1.id_article = lc2.id_article AND lc1.id_commande <> lc2.id_commande;
    RETURN _nbr_articles;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION examen.ajouter_article_trigger () RETURNS TRIGGER AS $$
BEGIN
    IF (NEW.quantite > (SELECT quantite_max FROM examen.articles WHERE id_article = NEW.id_article)) THEN
        RAISE EXCEPTION 'La quantité commandée est supérieure à la quantité maximale';
    END IF;
    IF EXISTS(SELECT * FROM examen.commandes co WHERE co.id_commande = NEW.id_commande AND co.type_commande = 'livraison' AND (SELECT sum(ar.prix) FROM examen.articles ar, examen.lignes_de_commande li WHERE li.id_article = NEW.id_article AND li.id_article = ar.id_article) < 1000) THEN
        RAISE EXCEPTION 'Une commande de type livraison ne peut pas dépasser 1000 euros';
    END IF;
    UPDATE examen.commandes SET poids_total = poids_total + (SELECT poids FROM examen.articles WHERE id_article = NEW.id_article) WHERE id_commande = NEW.id_commande;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER ajouter_article_trigger
    BEFORE INSERT ON examen.lignes_de_commande
    FOR EACH ROW
    EXECUTE PROCEDURE examen.ajouter_article_trigger();

CREATE OR REPLACE VIEW examen.afficher_commandes AS
    SELECT co.id_commande, co.date_commande, COALESCE(sum(li.id_article), 0), co.nom_client
    FROM examen.commandes co
        LEFT OUTER JOIN examen.lignes_de_commande li on co.id_commande = li.id_commande
    GROUP BY co.id_commande, co.date_commande
    ORDER BY co.date_commande;