SELECT articles.id   as id,
       articles.name as name,
       text,
       publicationTime,
       authorId,
       u.name        as authorName,
       u.surname     as authorSurname
FROM articles
         LEFT JOIN users u on u.id = articles.authorId
WHERE articles.id = ?