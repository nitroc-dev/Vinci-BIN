1) SELECT *
FROM bd1.albums

2) SELECT isbn,titre,scenariste,dessinateur,annee_edition
FROM bd1.albums 

3) SELECT *
FROM bd1.albums
WHERE editeur='Dupuis' 

4) SELECT DISTINCT titre 
FROM bd1.albums
WHERE scenariste='Goscinny' 

5) SELECT DISTINCT titre,editeur
FROM bd1.albums
WHERE scenariste='Uderzo' OR dessinateur='Uderzo' OR coloriste='Uderzo'

6) SELECT * 
FROM bd1.albums
WHERE coloriste IS NULL 

7) SELECT DISTINCT editeur
FROM bd1.albums
WHERE annee_edition=1978  

8) SELECT DISTINCT scenariste,dessinateur
FROM bd1.albums
WHERE (scenariste != dessinateur) AND editeur='Dargaud'  

9) SELECT *
FROM bd1.albums
WHERE scenariste = dessinateur AND coloriste!=scenariste  

10) SELECT *
FROM bd1.albums
WHERE scenariste = dessinateur AND dessinateur = coloriste  

11) SELECT *
FROM bd1.albums
WHERE 
(scenariste = dessinateur AND dessinateur = coloriste) 
OR (dessinateur IS NULL AND coloriste IS NULL AND scenariste IS NOT NULL) 
OR (dessinateur IS NULL AND scenariste IS NULL AND coloriste IS NOT NULL)
OR (scenariste IS NULL AND coloriste IS NULL AND dessinateur IS NOT NULL)
OR (scenariste IS NULL AND dessinateur = coloriste) 
OR (dessinateur IS NULL AND scenariste = coloriste)
OR (coloriste IS NULL AND scenariste = dessinateur)

12) SELECT DISTINCT scenariste 
FROM bd1.albums
WHERE annee_edition>1990 AND prix < 8 AND scenariste IS NOT NULL

13) SELECT DISTINCT titre
FROM bd1.albums
WHERE (annee_edition>1999 OR annee_edition<1990) 
AND editeur!='Casterman'
AND (coloriste IS NULL OR coloriste=dessinateur)    

14) SELECT DISTINCT titre 
FROM bd1.albums
WHERE (editeur !='Casterman' AND editeur!='Dupuis')
AND (scenariste !=coloriste AND coloriste!=dessinateur AND dessinateur!=scenariste)    

15) SELECT *
FROM bd1.albums
WHERE (serie='Astérix' AND editeur='Dargaud') 
OR (serie='Tintin' AND (editeur='Casterman' OR editeur='Le Lombard'))
OR (scenariste IS NULL AND dessinateur IS NULL AND coloriste IS NULL)

16) SELECT DISTINCT titre
FROM bd1.albums
WHERE titre LIKE '%César%'

17) SELECT DISTINCT coloriste
FROM bd1.albums
WHERE lower(coloriste) LIKE 'de%'    

18) SELECT DISTINCT titre,annee_edition
FROM bd1.albums 
WHERE serie='Astérix'
ORDER BY annee_edition ASC  

19) SELECT DISTINCT titre
FROM bd1.albums
WHERE serie='Astérix'
ORDER BY titre ASC 

20) SELECT isbn,titre, editeur, annee_edition
FROM bd1.albums
ORDER BY editeur,annee_edition ASC 

21) SELECT DISTINCT titre,prix
FROM bd1.albums
WHERE editeur='Dupuis'
ORDER BY prix DESC 

22) SELECT min(annee_edition)
FROM bd1.albums  

23) SELECT max(prix)
FROM bd1.albums
WHERE dessinateur!='Uderzo'  

24) SELECT count(*)
FROM bd1.albums
WHERE editeur='Casterman' 

25) SELECT max(annee_edition) - min(annee_edition)
FROM bd1.albums 

26) SELECT sum(prix) * 3 * 0.75
FROM bd1.albums
WHERE editeur='Blake et Mortimer'    

27) SELECT max(prix) = avg(prix)
FROM bd1.albums
WHERE serie = 'Tintin'  

28) SELECT count(DISTINCT serie)
FROM bd1.albums   

29) SELECT count(serie)
FROM bd1.albums  

30) SELECT count(*) - count(serie)
FROM bd1.albums   

31) SELECT count(*), min(annee_edition), max(annee_edition)
FROM bd1.albums
WHERE scenariste='Uderzo' OR dessinateur='Uderzo' OR coloriste='Uderzo'  

32) SELECT avg(prix)
FROM bd1.albums
WHERE editeur='Dupuis' AND annee_edition >= 1990 AND annee_edition <= 1999 

33) SELECT avg(prix)
FROM bd1.albums
WHERE editeur='Dupuis' AND (annee_edition < 1990 OR annee_edition > 1999) 

34) SELECT sum(prix)
FROM bd1.albums
WHERE (scenariste='Goscinny' OR dessinateur='Uderzo') 

35) SELECT sum(prix)
FROM bd1.albums
WHERE scenariste !='Goscinny' AND scenariste !='Uderzo' 