-- 1
SELECT DISTINCT au.au_id, au.au_fname, au.au_lname
FROM authors au, titleauthor ta
WHERE au.au_id = ta.au_id
AND au.au_id NOT IN (SELECT ta2.au_id
                     FROM titleauthor ta2, titles ti
                     WHERE ta.title_id = ta2.title_id
                     AND (ti.type != 'business' OR ti.type IS NULL));

-- 2
SELECT a.au_id, a.au_lname, a.au_fname, COUNT(t.title_id) AS nbr_livres
FROM authors a LEFT OUTER JOIN titleauthor ta on a.au_id = ta.au_id
               LEFT OUTER JOIN titles t on ta.title_id = t.title_id
AND (lower(t.type) = 'psychology' OR lower(t.type) = 'sociology')
GROUP BY a.au_id, a.au_lname, a.au_fname;

-- 3
