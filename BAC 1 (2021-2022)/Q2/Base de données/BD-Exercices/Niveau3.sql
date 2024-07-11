1) SELECT DISTINCT au.*
FROM bd3.auteurs au, bd3.participations pa , bd3.albums al
WHERE au.num=pa.num_auteur AND al.isbn=pa.isbn
AND pa.participe = 'd' AND al.titre = 'Astérix chez les Belges'

2) SELECT DISTINCT ed.*
FROM bd3.auteurs au, bd3.participations pa, bd3.albums al, bd3.editeurs ed
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND au.nom = 'Goscinny'

3) SELECT DISTINCT au.*
FROM bd3.auteurs au, bd3.participations pa, bd3.albums al, bd3.editeurs ed
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND (pa.participe = 'd' OR pa.participe = 'c')
AND ed.nom='Dupuis'

4) SELECT count(DISTINCT pa.num_auteur)
FROM bd3.participations pa, bd3.albums al
WHERE pa.isbn=al.isbn AND al.titre='Coke en Stock'

5) SELECT DISTINCT al.titre, al.prix
FROM bd3.albums al, bd3.participations pa, bd3.auteurs au
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn)
AND (pa.participe='d' AND au.nom='Uderzo')
AND (al.annee_edition>=1985 AND al.annee_edition<=1995)

6) SELECT DISTINCT al.*
FROM bd3.participations pa, bd3.albums al, bd3.editeurs ed
WHERE (pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND ed.nom='Casterman' AND pa.participe='c'

7) SELECT max(al.annee_edition)
FROM bd3.albums al, bd3.participations pa, bd3.auteurs au
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn)
AND au.nom='Goscinny'

8) SELECT DISTINCT au.*
FROM bd3.participations pa, bd3.auteurs au
WHERE au.num=pa.num_auteur
AND pa.participe = 'd'
ORDER BY au.nom

9) SELECT DISTINCT au.*,ed.nom
FROM bd3.participations pa, bd3.auteurs au, bd3.albums al, bd3.editeurs ed
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND pa.participe = 'd'
ORDER BY au.nom, ed.nom

10) SELECT DISTINCT au.nom, au.num
FROM bd3.albums al, bd3.participations pa, bd3.auteurs au
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn)
AND al.serie='Astérix'
AND al.annee_edition<1992

11) SELECT DISTINCT pa.participe
FROM bd3.participations pa, bd3.auteurs au
WHERE (au.num=pa.num_auteur)
AND au.nom='Cosey'

12) SELECT DISTINCT pa.participe
FROM bd3.participations pa, bd3.auteurs au, bd3.albums al, bd3.editeurs ed
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND au.nom='Uderzo'
AND ed.nom ='Albert René'

13) SELECT DISTINCT au.*
FROM bd3.albums al, bd3.participations pa, bd3.auteurs au, bd3.participations pa2
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND pa2.isbn=al.isbn AND
au.num=pa2.num_auteur)
AND pa.participe<>pa2.participe

14) SELECT DISTINCT al.*
FROM bd3.albums al, bd3.albums al2
WHERE al.annee_edition=al2.annee_edition
AND al2.titre='La marque jaune' AND al2.titre<>al.titre

15) SELECT count(*), serie
FROM bd3.albums
WHERE serie IS NOT NULL
GROUP BY serie

16) SELECT ed.*, min(al.annee_edition), max(al.annee_edition)
FROM bd3.editeurs ed , bd3.albums al
WHERE ed.num =al.num_editeur
GROUP BY ed.num

17) SELECT au.* , count(pa.isbn)
FROM bd3.participations pa, bd3.auteurs au
WHERE pa.num_auteur = au.num
AND pa.participe='d'
GROUP BY au.num

18) SELECT count(al.*) AS “nombre d’albums”, avg(al.prix) AS “prix moyen”, al.annee_edition
FROM bd3.albums al
WHERE annee_edition IS NOT NULL
GROUP BY al.annee_edition
ORDER BY al.annee_edition

19) SELECT ed.num, ed.nom, ed.pays, count(al.*)
FROM bd3.editeurs ed, bd3.albums al
WHERE al.num_editeur = ed.num
AND al.prix <10
GROUP BY ed.num

20) SELECT ed.*, count(al.*), max(al.prix)
FROM bd3.albums al, bd3.editeurs ed, bd3.participations pa, bd3.auteurs au
WHERE (au.num=pa.num_auteur AND pa.isbn=al.isbn AND al.num_editeur=ed.num)
AND au.nom='Uderzo' AND pa.participe ='d'
GROUP BY ed.num