1) SELECT isbn, titre, scenariste, dessinateur, num_editeur
FROM bd2.albums 

2) SELECT bd2.albums.isbn, bd2.albums.titre, bd2.albums.scenariste, bd2.albums.dessinateur, bd2.editeurs.nom
FROM bd2.editeurs, bd2.albums
WHERE bd2.editeurs.num = bd2.albums.num_editeur  

3) SELECT al.isbn, al.titre, ed.nom
FROM bd2.editeurs ed, bd2.albums al
WHERE al.num_editeur = ed.num AND ed.pays='be'      

4) SELECT al.isbn, al.titre, ed.nom
FROM bd2.editeurs ed, bd2.albums al
WHERE ed.pays='be' 

5) SELECT al.*
FROM bd2.albums al, bd2.editeurs ed
WHERE al.num_editeur = ed.num AND al.serie='Astérix' AND ed.nom !='Dargaud'    

6) SELECT DISTINCT ed.num, ed.nom
FROM bd2.editeurs ed, bd2.albums al
WHERE ed.num = al.num_editeur AND al.annee_edition='1999' AND al.prix > 10     

7) SELECT DISTINCT ed.num, ed.nom
FROM bd2.editeurs ed, bd2.albums al
WHERE (al.dessinateur='Goscinny' OR al.scenariste='Goscinny' OR al.coloriste='Goscinny') AND ed.num = al.num_editeur  

8) SELECT ed.num, ed.nom
FROM bd2.editeurs ed
WHERE (ed.pays!='be' OR ed.pays IS NULL)     

9) SELECT al.*
FROM bd2.albums al, bd2.editeurs ed
WHERE al.num_editeur = ed.num
AND (ed.pays= 'be' OR ed.pays ='fr')
AND ((al.serie !='Tintin' AND al.serie!='Astérix') OR al.serie IS NULL)

10) SELECT DISTINCT al.dessinateur
FROM bd2.editeurs ed, bd2.albums al
WHERE ed.nom='Dupuis' AND ed.num = al.num_editeur  
ORDER BY al.dessinateur ASC 

11) SELECT al.isbn, al.titre, al.dessinateur, al.annee_edition
FROM bd2.albums al, bd2.editeurs ed
WHERE ed.num = al.num_editeur AND ed.nom='Dupuis' AND al.annee_edition >= 1990 AND al.annee_edition <= 2000
ORDER BY dessinateur, annee_edition ASC 

12) SELECT DISTINCT ed.*
FROM bd2.albums al, bd2.editeurs ed
WHERE al.num_editeur = ed.num
AND (al.dessinateur IS NULL AND al.coloriste IS NULL AND al.scenariste IS NULL) 

13) SELECT al.isbn
FROM bd2.editeurs ed, bd2.albums al
WHERE ed.nom=al.serie AND ed.num = al.num_editeur

14) SELECT DISTINCT ed.*
FROM bd2.albums al, bd2.editeurs ed
WHERE ed.nom = al.dessinateur OR ed.nom = al.scenariste OR ed.nom = al.coloriste  

15) SELECT count(*) 
FROM bd2.albums al, bd2.editeurs ed
WHERE ed.pays='be' AND al.dessinateur!=al.scenariste AND ed.num = al.num_editeur  

16) SELECT min(annee_edition)
FROM bd2.editeurs ed, bd2.albums al
WHERE ed.num=al.num_editeur AND ed.nom='Casterman'  

17) SELECT avg(2019-al.annee_edition)
FROM bd2.albums al, bd2.editeurs ed
WHERE al.num_editeur = ed.num
AND ed.pays ='be'  

18) SELECT min(al.annee_edition)
FROM bd2.albums al
WHERE prix <= 5 

19) SELECT count(al.*)
FROM bd2.albums al
WHERE scenariste IS NULL AND dessinateur IS NULL AND coloriste IS NULL 

20) SELECT avg(al.prix)
FROM bd2.albums al, bd2.editeurs ed
WHERE ed.num = al.num_editeur AND ed.pays='be' 

21) SELECT sum(al.prix), count(al.*), avg(al.prix)
FROM bd2.albums al, bd2.editeurs ed
WHERE ed.pays='fr' AND prix < 8 AND ed.num = al.num_editeur   

22) SELECT max(al.annee_edition) - min(al.annee_edition) + 1
FROM bd2.albums al
WHERE al.scenariste='Franquin'   

23) SELECT DISTINCT al2.*
FROM bd2.albums al, bd2.editeurs ed, bd2.albums al2
WHERE ed.num = al.num_editeur AND 
ed.nom = 'Fluide glacial' AND 
al.titre = 'Idées Noires' AND 
al.prix = al2.prix AND
al.isbn != al2.isbn