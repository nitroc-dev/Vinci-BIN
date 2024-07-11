1) SELECT al.serie , count(al.*)
FROM bd3.albums al, bd3.editeurs ed
WHERE al.num_editeur = ed.num
AND ed.nom = 'Dupuis' AND al.serie IS NOT NULL
GROUP BY al.serie

2) SELECT DISTINCT ed.*
FROM bd3.editeurs ed, bd3.albums al
WHERE al.num_editeur = ed.num
AND annee_edition = 1978
GROUP BY ed.num
HAVING count(DISTINCT al.*) >1

3) SELECT ed.*, count(al.*) AS "Nombre d'albums"
FROM bd3.editeurs ed, bd3.albums al
WHERE ed.num= al.num_editeur
GROUP BY ed.num

4) Combien y a-t-il d’albums par scenariste ?
SELECT au.*, count(pa.isbn)
FROM bd3.participations pa, bd3.auteurs au
WHERE au.num = pa.num_auteur
AND pa.participe='s'
GROUP BY au.num

5)SELECT DISTINCT au.*, count(pa.isbn)
FROM bd3.participations pa, bd3.auteurs au
WHERE au.num = pa.num_auteur
GROUP BY au.num

6) SELECT al.*
FROM bd3.albums al
WHERE al.isbn NOT IN (SELECT pa.isbn FROM bd3.participations pa WHERE pa.participe = 'c')

7) SELECT DISTINCT au.*
FROM bd3.auteurs au, bd3.albums al, bd3.participations pa
WHERE au.num = pa.num_auteur AND pa.isbn = al.isbn
AND al.annee_edition>1990 AND al.prix<8 AND pa.participe='s'

8) SELECT al.serie, count(al.*)
FROM bd3.albums al, bd3.participations pa, bd3.auteurs au
WHERE pa.isbn = al.isbn AND au.num = pa.num_auteur
AND au.nom ='Franquin' AND pa.participe ='d' AND al.serie IS NOT NULL
GROUP BY al.serie

9) SELECT avg(al.prix), al.annee_edition
FROM bd3.albums al
WHERE annee_edition IS NOT NULL
GROUP BY al.annee_edition

10) SELECT al.serie, count(DISTINCT pa.*)
FROM bd3.albums al, bd3.participations pa, bd3.editeurs ed
WHERE pa.isbn = al.isbn AND al.num_editeur=ed.num
AND pa.participe='s' AND ed.pays='be' AND al.serie IS NOT NULL
GROUP BY al.serie

11) SELECT min(al.annee_edition)
FROM bd3.albums al, bd3.editeurs ed
WHERE al.num_editeur = ed.num AND ed.nom = 'Casterman'

12) SELECT avg(al.prix)
FROM bd3.albums al, bd3.editeurs ed
WHERE al.num_editeur =ed.num AND ed.pays=’be’

13) SELECT DISTINCT al.*
FROM bd3.albums al, bd3.participations pa
WHERE pa.isbn=al.isbn
GROUP BY al.isbn
HAVING count ( DISTINCT pa.num_auteur)=1

14) SELECT DISTINCT ed.*
FROM bd3.albums al, bd3.editeurs ed
WHERE al.num_editeur=ed.num
AND al.isbn NOT IN
(SELECT pa.isbn FROM bd3.participations pa)

15) SELECT min(al.annee_edition)
FROM bd3.albums al, bd3.participations pa, bd3.participations pa1, bd3.participations pa2
WHERE (al.isbn = pa.isbn) AND (al.isbn = pa1.isbn) AND (al.isbn = pa2.isbn)
AND (pa.participe ='s') AND (pa1.participe = 'd') AND (pa2.participe ='c')
AND (pa.num_auteur=pa1.num_auteur) AND (pa2.num_auteur=pa1.num_auteur)

16) SELECT DISTINCT al.titre
FROM bd3.albums al
WHERE al.annee_edition =1977 AND al.prix IN
(SELECT min(al1.prix) FROM bd3.albums al1 WHERE al1.annee_edition =1977)

17) SELECT al.titre, al.annee_edition AS "Première édition", al2.annee_edition AS "Deuxième
édition"
FROM bd3.albums al, bd3.albums al2
WHERE al.titre = al2.titre AND al.isbn<al2.isbn

18) SELECT at.*
FROM bd3.auteurs at
WHERE at.num IN 
(SELECT par.num_auteur 
FROM bd3.participations par, bd3.albums alb
WHERE par.participe='d'
AND alb.isbn=par.isbn
GROUP BY par.num_auteur
HAVING COUNT (DISTINCT alb.serie)>1) 

19) SELECT DISTINCT al.isbn, al.titre, count(pa.participe) AS "Nombre de participants"
FROM bd3.albums al, bd3.participations pa
WHERE al.isbn=pa.isbn
AND pa.participe IS NOT NULL
GROUP BY al.isbn