-- Requete pour résultats
SELECT a.state, pu.state FROM authors a, publishers pu WHERE a.state = pu.state;

-- 1
SELECT t.title, t.price, p.pub_name
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id;

-- 2
SELECT t.title, t.price, p.pub_name
FROM titles t, publishers p
WHERE t.pub_id = p.pub_id
AND t.type = 'psychology';

-- 3
SELECT DISTINCT a.au_fname, a.au_lname
FROM authors a, titles t, titleauthor ta
WHERE a.au_id = ta.au_id
AND ta.title_id = t.title_id;

-- 4
SELECT DISTINCT a.state
FROM authors a, titles t, titleauthor ta
WHERE a.au_id = ta.au_id
AND ta.title_id = t.title_id;

-- 5
SELECT DISTINCT s.stor_name, s.stor_address
FROM stores s
WHERE s.stor_id IN (SELECT sd.stor_id FROM salesdetail sd, sales sa WHERE sd.ord_num = sa.ord_num AND sa.date BETWEEN '1991-10-31' AND '1991-12-01');

-- 6
SELECT ti.title_id, ti.title, ti.type, ti.total_sales, ti.pubdate, ti.price, ti.pub_id
FROM titles ti, publishers pu
WHERE ti.type = 'psychology'
AND ti.price < 20
AND pu.pub_id = ti.pub_id
AND pu.pub_name NOT SIMILAR TO '[Aa]lgo%';

-- 7
SELECT DISTINCT ti.title
FROM titles ti, authors au, titleauthor ta
WHERE ti.title_id = ta.title_id
AND au.au_id = ta.au_id
AND au.state = 'CA';

-- 8
SELECT DISTINCT au.au_id, au.au_fname, au.au_lname
FROM authors au, publishers pu, titles ti, titleauthor ta
WHERE au.au_id = ta.au_id
AND ta.title_id = ti.title_id
AND ti.pub_id = pu.pub_id
AND pu.state = 'CA';

-- 9 (à revoir)
SELECT DISTINCT au.au_id, au.au_fname, au.au_lname
FROM authors au, publishers pu, titles ti, titleauthor ta
WHERE au.au_id = ta.au_id
AND ta.title_id = ti.title_id
AND ti.pub_id = pu.pub_id
AND pu.state = au.state;

-- 10
SELECT pu.pub_id, pu.pub_name
FROM publishers pu, titles ti, salesdetail sd, sales sa
WHERE pu.pub_id = ti.pub_id
AND ti.title_id = sd.title_id
AND sd.stor_id = sa.stor_id
AND sa.date BETWEEN '1990-11-01' AND '1991-3-1';

-- 11
SELECT st.stor_id, st.stor_name
FROM stores st, salesdetail sd, titles ti
WHERE st.stor_id = sd.stor_id
AND sd.title_id = ti.title_id
AND ti.title LIKE '%[Cc]ook%';

-- 12
SELECT EXISTS(SELECT t1.title_id, t1.title
                    FROM titles t1,
                         titles t2,
                         publishers pu
                    WHERE t1.title_id <> t2.title_id
                      AND t1.pub_id = pu.pub_id
                      AND t2.pub_id = pu.pub_id
                      AND t2.pubdate = t1.pubdate);

-- 13
SELECT EXISTS(SELECT DISTINCT au.au_id, au.au_fname, au.au_lname
                    FROM authors au,
                       titleauthor ta1,
                       titleauthor ta2,
                       titles ti1,
                       titles ti2
                    WHERE au.au_id = ta1.au_id
                        AND au.au_id = ta2.au_id
                        AND ta1.au_id = ta2.au_id
                        AND ta1.title_id = ti1.title_id
                        AND ta2.title_id = ti2.title_id
                        AND ti1.pub_id <> ti2.pub_id);

-- 14
SELECT EXISTS(SELECT DISTINCT ti.title_id, ti.title
                    FROM titles ti,
                         salesdetail sd,
                         sales sa
                    WHERE ti.title_id = sd.title_id
                        AND sd.stor_id = sa.stor_id
                        AND sa.date < ti.pubdate);

-- 15
SELECT DISTINCT st.stor_id, st.stor_name
FROM stores st, salesdetail sd, titles ti, titleauthor ta, authors au
WHERE st.stor_id = sd.stor_id
AND au.au_id = ta.au_id
AND sd.title_id = ta.title_id
AND ta.au_id = au.au_id
AND au.au_fname SIMILAR TO '[Aa]nne'
AND au.au_lname SIMILAR TO '[Rr]inger';

-- 16
SELECT DISTINCT au.state
FROM authors au, titleauthor ta, salesdetail sd, sales sa, stores st
WHERE au.au_id = ta.au_id
AND ta.title_id = sd.title_id
AND sd.ord_num = sa.ord_num
AND sd.stor_id = sa.stor_id
AND sa.stor_id = st.stor_id
AND st.state = 'CA'
AND st.state IS NOT NULL
AND sa.date BETWEEN '1991-02-01' AND '1991-02-28';

-- 17
SELECT DISTINCT st1.stor_id, st1.stor_name, st2.stor_id, st2.stor_name
FROM stores st1, stores st2, salesdetail sd1, salesdetail sd2, titleauthor ta1, titleauthor ta2
WHERE st1.stor_id = sd1.stor_id
AND st2.stor_id = sd2.stor_id
AND sd1.title_id = ta1.title_id
AND sd2.title_id = ta2.title_id
AND ta1.au_id = ta2.au_id
AND st1.state = st2.state
AND st1.stor_id < st2.stor_id;

-- 18
SELECT DISTINCT au1.au_id, au1.au_fname, au1.au_lname, au2.au_id, au2.au_fname, au2.au_lname
FROM authors au1, authors au2, titleauthor ta1, titleauthor ta2
WHERE au1.au_id = ta1.au_id
AND au2.au_id = ta2.au_id
AND au1.au_id < au2.au_id
AND ta1.title_id = ta2.title_id;

-- 19
SELECT ti.title, st.stor_name, ti.price, sa.qty, ti.price * sa.qty AS "Montant total", ti.price * sa.qty * 0.02 AS "Eco-taxe totale", sa.stor_id, sa.ord_num, sa.title_id
FROM salesdetail sa, titles ti, stores st
WHERE sa.title_id = ti.title_id
AND sa.stor_id = st.stor_id
