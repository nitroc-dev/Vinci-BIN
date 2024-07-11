-- Requete pour résultats
SELECT * FROM authors;

-- 1
SELECT a.au_id, a.au_lname
FROM authors a
WHERE a.city = 'Oakland';

-- 2
SELECT a.au_id, a.au_lname, a.address
FROM authors a
WHERE a.au_fname LIKE 'A%';

-- 3
SELECT a.au_id, a.au_lname, a.address
FROM authors a
WHERE a.phone IS NULL;

-- 4
SELECT EXISTS (SELECT a.au_id, a.au_lname, a.au_fname, a.phone, a.address, a.city, a.state, a.country
FROM authors a
WHERE a.state = 'CA'
AND a.phone NOT LIKE '415%';);

-- 5
SELECT a.au_id, a.au_lname, a.au_fname, a.phone, a.address, a.city, a.state, a.country
FROM authors a
WHERE a.country = 'BEL'
OR a.country = 'LUX'
OR a.country = 'NLD';

-- 6
SELECT DISTINCT t.pub_id
FROM titles t
AND t.type = 'psychology';

-- 7
SELECT DISTINCT t.pub_id
FROM titles t
AND t.type = 'psychology'
AND (t.price < 10 OR t.price > 25);

-- 8
SELECT DISTINCT a.city
FROM authors a
WHERE a.state = 'CA'
AND a.au_fname SIMILAR TO 'Albert|%er';

-- 9
SELECT a.state, a.country
FROM authors a
WHERE a.state IS NOT NULL
AND a.country <> 'USA';

-- 10
SELECT DISTINCT t.type
FROM titles t
WHERE t.price < 15
AND t.type IS NOT NULL;