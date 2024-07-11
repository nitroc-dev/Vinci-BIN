-- 2
SELECT s.stor_id, s.stor_name, s.stor_address, s.city, s.state, s.country, SUM(sd.qty*t.price) AS chiffre_affaire
FROM stores s LEFT OUTER JOIN salesdetail sd on s.stor_id = sd.stor_id LEFT OUTER JOIN titles t on sd.title_id = t.title_id
GROUP BY s.stor_id, s.stor_name, s.stor_address, s.city, s.state, s.country
ORDER BY chiffre_affaire DESC;

-- 3
SELECT ti.title_id, ti.title, ti.type, pu.pub_name
FROM titles ti, publishers pu
WHERE ti.pub_id = pu.pub_id
AND ti.price > 20
ORDER BY ti.type;

-- 4
SELECT DISTINCT t.title_id, t.title, t.type, a.au_id, a.au_lname, t.price
FROM titles t
    LEFT OUTER JOIN titleauthor ta ON (ta.title_id = t.title_id)
    LEFT OUTER JOIN authors a ON (ta.au_id = a.au_id)
WHERE t.price > 20
ORDER BY t.type;

-- 5
SELECT pu.city
FROM publishers pu
WHERE pu.state = 'CA'
UNION (SELECT au.city FROM authors au WHERE au.state = 'CA')
EXCEPT (SELECT st.city FROM stores st WHERE st.state = 'CA');

-- 6
(SELECT a.au_fname, a.au_lname, count(t.title_id)
FROM authors a, titles t, titleauthor ta
WHERE a.au_id = ta.au_id AND ta.title_id = t.title_id
AND t.price > 20
GROUP BY a.au_fname, a.au_lname)
UNION ALL
(SELECT a.au_fname, a.au_lname
FROM authors a WHERE a.au_id NOT IN (SELECT ta.au_id 
									FROM titleauthor ta2, titles t2
									WHERE ta2.title_id = t2.title_id
									AND t2.price > 20)
)
ORDER BY 3 DESC,1;