DROP SCHEMA IF EXISTS examen CASCADE;

CREATE SCHEMA examen;

CREATE TYPE examen.sexe AS ENUM ('M', 'F');

CREATE TABLE examen.concerts (
                                 id_concert SERIAL PRIMARY KEY,
                                 date_concert DATE NOT NULL,
                                 nom_artiste VARCHAR NOT NULL CHECK ( nom_artiste <> '' ),
                                 nom_salle VARCHAR NOT NULL CHECK ( nom_salle <> '' ),
                                 nbr_places INTEGER NOT NULL CHECK ( nbr_places > 0 ),
                                 complet BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE examen.clients (
                                id_client SERIAL PRIMARY KEY,
                                nom VARCHAR NOT NULL CHECK ( nom <> '' ),
                                prenom VARCHAR NOT NULL CHECK ( prenom <> '' ),
                                sexe examen.sexe NOT NULL
);

CREATE TABLE examen.reservations (
                                     num_reservation INTEGER NOT NULL,
                                     id_concert INTEGER REFERENCES examen.concerts(id_concert) NOT NULL,
                                     id_client INTEGER REFERENCES examen.clients(id_client) NOT NULL,
                                     nbr_tickets INTEGER NOT NULL CHECK ( nbr_tickets > 0 AND nbr_tickets <= 4 ),
                                     PRIMARY KEY (num_reservation, id_concert)
);

CREATE OR REPLACE FUNCTION examen.reserver (_id_client INTEGER, _id_concert INTEGER, _nbr_tickets INTEGER) RETURNS BOOLEAN AS $$
DECLARE
    nbr_reservations INTEGER;
BEGIN
    SELECT count(*) FROM examen.reservations re WHERE re.id_concert = _id_concert INTO nbr_reservations;
    nbr_reservations := nbr_reservations + 1;
    INSERT INTO examen.reservations (num_reservation , id_concert, id_client, nbr_tickets) VALUES (nbr_reservations, _id_concert, _id_client, _nbr_tickets);
    RETURN (SELECT co.complet FROM examen.concerts co WHERE co.id_concert = _id_concert);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION examen.reserver_trigger () RETURNS TRIGGER AS $$
DECLARE
    _date_concert DATE;
    _nbr_places INTEGER;
BEGIN
    _date_concert := (SELECT co.date_concert FROM examen.concerts co WHERE co.id_concert = NEW.id_concert);
    _nbr_places := (SELECT co.nbr_places FROM examen.concerts co WHERE co.id_concert = NEW.id_concert);
    IF ((SELECT sum(re.nbr_tickets) FROM examen.reservations re WHERE re.id_concert = NEW.id_concert AND re.id_client = NEW.id_client) + NEW.nbr_tickets) > 4 THEN
        RAISE EXCEPTION 'Vous ne pouvez pas réserver plus de 4 billets par concert';
    END IF;
    IF EXISTS (SELECT * FROM examen.reservations re, examen.concerts co WHERE re.id_client = NEW.id_client AND co.date_concert = _date_concert AND re.id_concert = co.id_concert AND re.id_concert <> NEW.id_concert) THEN
        RAISE EXCEPTION 'Vous ne pouvez pas réserver pour un autre concert le même jour';
    END IF;
    IF (SELECT sum(re.nbr_tickets) FROM examen.reservations re WHERE re.id_concert = NEW.id_concert) + NEW.nbr_tickets > _nbr_places THEN
        RAISE EXCEPTION 'Il n''y a plus de place pour ce concert';
    END IF;
    IF (SELECT sum(re.nbr_tickets) FROM examen.reservations re WHERE re.id_concert = NEW.id_concert) + NEW.nbr_tickets = _nbr_places THEN
        UPDATE examen.concerts SET complet = TRUE WHERE id_concert = NEW.id_concert;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reserver_trigger BEFORE INSERT ON examen.reservations FOR EACH ROW EXECUTE PROCEDURE examen.reserver_trigger();

CREATE OR REPLACE VIEW examen.afficher_concerts AS
SELECT co.date_concert, co.nom_salle, COALESCE(sum(re.nbr_tickets), 0), co.nom_artiste
FROM examen.concerts co
         LEFT OUTER JOIN examen.reservations re on co.id_concert = re.id_concert
GROUP BY co.date_concert, co.nom_salle, co.nom_artiste
ORDER BY co.date_concert;
