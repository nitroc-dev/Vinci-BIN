-- 1
SELECT a.au_lname, a.au_fname, a.address, t.title_id, coalesce(t.title, 'Aucun livre') as title
FROM authors a
         LEFT OUTER JOIN titleauthor ta ON ta.au_id = a.au_id
         LEFT OUTER JOIN titles t ON ta.title_id = t.title_id
ORDER BY (t.title_id);