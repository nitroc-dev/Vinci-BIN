-- 1
SELECT ti.title_id, ti.title
FROM titles ti, publishers pu
WHERE ti.pub_id = pu.pub_id
AND ti.price = (SELECT MAX(t.price)
                FROM titles t
                WHERE t.pub_id = pu.pub_id
                AND pu.pub_name SIMILAR TO 'Algodata Infosystems');

-- 2
SELECT DISTINCT ti.title_id, ti.title
FROM titles ti, salesdetail sa1, salesdetail sa2
WHERE ti.title_id = sa1.title_id
AND ti.title_id = sa2.title_id
AND sa1.stor_id < sa2.stor_id;

-- 3
SELECT ti.title_id, ti.title
FROM titles ti
WHERE ti.price > (SELECT AVG(t.price) * 1.5
                  FROM titles t
                  WHERE ti.type = t.type);

-- 4
SELECT DISTINCT au.au_id, au.au_fname, au.au_lname
FROM authors au, publishers pu, titles ti, titleauthor ta
WHERE au.au_id = ta.au_id
AND ta.title_id = ti.title_id
AND ti.pub_id = pu.pub_id
AND pu.state = au.state;

-- 5
SELECT pu.pub_id, pu.pub_name
FROM publishers pu
WHERE pu.pub_id NOT IN (SELECT ti.pub_id FROM titles ti);

-- 6
SELECT p.pub_id, p.pub_name
FROM publishers p, titles t
WHERE p.pub_id=t.pub_id
GROUP BY p.pub_id
HAVING COUNT(*)>=ALL(
    SELECT COUNT(*)
    FROM titles t
    GROUP BY p.pub_id);

-- 7
SELECT p.pub_id, p.pub_name
FROM publishers p
WHERE p.pub_id NOT IN (SELECT t.pub_id
                       FROM titles t, salesdetail sd
                       WHERE sd.title_id = t.title_id);

-- 8
SELECT DISTINCT ti.title_id, ti.title
FROM titles ti, publishers pu, salesdetail sd
WHERE ti.pub_id = pu.pub_id
AND sd.title_id = ti.title_id
AND pu.state = 'CA'
AND ti.title_id IN (SELECT ta.title_id
                    FROM titleauthor ta, authors au
                    WHERE ta.au_id = au.au_id
                    AND au.state = 'CA')
AND ti.title_id NOT IN (SELECT sd.title_id
                        FROM stores st, salesdetail sd
                        WHERE sd.stor_id = st.stor_id
                        AND (st.state != 'CA' OR st.state IS NULL));

-- 9
SELECT DISTINCT ti.title
FROM titles ti, salesdetail sd, sales sa
WHERE ti.title_id = sd.title_id
AND sd.ord_num = sa.ord_num
AND sd.stor_id = sa.stor_id
AND sa.date = (SELECT max(sa.date)
               FROM titles ti, salesdetail sd, sales
               WHERE ti.title_id = sd.title_id
               AND sd.ord_num = sa.ord_num
               AND sd.stor_id = sa.stor_id);

-- 10
SELECT st1.stor_id, st1.stor_name
FROM stores st1
WHERE NOT EXISTS (
    SELECT distinct t.title
    FROM stores st2, salesdetail sd, titles t
    WHERE st2.stor_id = sd.stor_id
    AND t.title_id = sd.title_id
    AND stor_name = 'Bookbeat'
    AND NOT EXISTS (
        SELECT *
        FROM salesdetail sd
        WHERE sd.title_id = t.title_id
        AND sd.stor_id = st1.stor_id))
AND stor_name != 'Bookbeat';

-- 11
SELECT DISTINCT au.city
FROM authors au
WHERE au.state = 'CA'
AND NOT EXISTS (SELECT s.city
                FROM stores s
                WHERE s.state = 'CA'
                AND s.city = au.city);

-- 12
SELECT DISTINCT p.pub_id, p.pub_name
	FROM publishers p
	WHERE (SELECT count(a.au_id)
		   FROM authors a
		   WHERE p.city = a.city) >= ALL (SELECT count(a2.au_id)
										  FROM authors a2
										  GROUP BY a2.city);

-- 13
SELECT DISTINCT t.title, t.title_id
FROM titles t, titleauthor ta
WHERE t.title_id = ta.title_id
AND t.title_id NOT IN (SELECT ta2.title_id
                       FROM authors a ,titleauthor ta2
                       WHERE a.state != 'CA'
                       AND ta2.au_id = a.au_id);

-- 14
SELECT t.title_id, t.title
FROM titles t
WHERE 0 = (SELECT count(a.au_id)
           FROM titleauthor ta, authors a
           WHERE ta.title_id = t.title_id
           AND ta.au_id = a.au_id
           AND a.state = 'CA');

-- 15
SELECT t.title_id, t.title
FROM titles t
WHERE 1 = (SELECT count(ta.au_id)
            FROM titleauthor ta
            WHERE ta.title_id = t.title_id);

-- 16
SELECT t.title_id, t.title
FROM titles t
WHERE 1 = (SELECT count(ta.au_id)
           FROM titleauthor ta, authors a
           WHERE ta.title_id = t.title_id
           AND a.au_id = ta.au_id
           AND a.state = 'CA');
AND 1 = (SELECT count(ta.au_id)
         FROM titleauthor ta
         WHERE ta.title_id = t.title_id);