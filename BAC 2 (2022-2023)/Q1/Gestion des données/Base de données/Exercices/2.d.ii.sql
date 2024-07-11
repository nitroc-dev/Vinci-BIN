-- 1
SELECT avg(ti.price) AS prix_moyen
FROM titles ti, publishers pu
WHERE pu.pub_id = ti.pub_id
AND pu.pub_name = 'Aglodata Infosystems';

-- 2
SELECT au.au_id, au.au_lname, au.au_fname, avg(ti.price)
FROM titles ti, authors au, titleauthor ta
WHERE ta.au_id = au.au_id
AND ti.title_id = ta.title_id
GROUP BY au.au_id, au.au_lname, au.au_fname;

-- 3
SELECT t.title_id, t.price, COUNT(DISTINCT ta.au_id) AS nombre_auteurs
FROM publishers p, titles t LEFT OUTER JOIN titleauthor ta
ON ta.title_id = t.title_id
WHERE p.pub_id = t.pub_id
AND p.pub_name = 'Algodata Infosystems'
GROUP BY  t.price, t.title_id;

-- 4
SELECT ti.title, ti.price, count(st.stor_id)
FROM titles ti
LEFT OUTER JOIN salesdetail s ON ti.title_id = s.title_id
LEFT OUTER JOIN stores st ON st.stor_id = s.stor_id
GROUP BY ti.title, ti.price;

-- 5
SELECT DISTINCT ti.title_id, ti.title
FROM titles ti, salesdetail sa1, salesdetail sa2
WHERE ti.title_id = sa1.title_id
AND ti.title_id = sa2.title_id
AND sa1.stor_id < sa2.stor_id;

-- 6
SELECT ti.type, SUM(ti.title_id), AVG(ti.price)
FROM titles ti
WHERE ti.type IS NOT NULL
GROUP BY ti.type;

-- 7
SELECT ti.title_id, ti.total_sales, SUM(sd.qty)
FROM titles ti
LEFT OUTER JOIN salesdetail sd on ti.title_id = sd.title_id
GROUP BY ti.title_id, ti.total_sales;

-- 8
SELECT ti.title_id, COALESCE(ti.total_sales), SUM(sd.qty)
FROM titles ti
LEFT OUTER JOIN salesdetail sd on ti.title_id = sd.title_id
GROUP BY ti.title_id, ti.total_sales
HAVING COALESCE(ti.total_sales) != SUM(sd.qty);

-- 9
SELECT DISTINCT  ti.title_id, ti.title
FROM titles ti, titleauthor ta1, titleauthor ta2, titleauthor ta3
WHERE ti.title_id = ta1.title_id
AND ti.title_id = ta2.title_id
AND ti.title_id = ta3.title_id
AND ta1.au_id != ta2.au_id
AND ta2.au_id != ta3.au_id
AND ta1.au_id != ta3.au_id;

-- 9 bis
SELECT ti.title_id, ti.title
FROM titles ti, titleauthor ta
WHERE ti.title_id = ta.title_id
GROUP BY ti.title_id, ti.title
HAVING COUNT(ta.au_id) >= 3;

-- 10
SELECT SUM(sa.qty)
FROM titles ti, publishers pu, salesdetail sa, stores st
WHERE ti.pub_id = pu.pub_id
AND ti.title_id = sa.title_id
AND st.stor_id = sa.stor_id
AND pu.state = 'CA'
AND st.state = 'CA'
AND ti.title_id IN (SELECT ta.title_id
                    FROM titleauthor ta, authors au
                    WHERE ta.au_id = au.au_id
                    AND au.state = 'CA');

