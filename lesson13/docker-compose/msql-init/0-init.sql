create table quotations
(
    id        int auto_increment
        primary key,
    text      text not null,
    author_id int  not null
);

INSERT INTO msqldb.quotations (id, text, author_id) VALUES (1, 'alias dicker=docker', 1);
INSERT INTO msqldb.quotations (id, text, author_id) VALUES (2, 'alias suka=sudo', 1);
INSERT INTO msqldb.quotations (id, text, author_id) VALUES (3, 'Separate independent high-level cyber-war military unit ', 1);
INSERT INTO msqldb.quotations (id, text, author_id) VALUES (4, 'Now I cannot even edit my photos', 2);
INSERT INTO msqldb.quotations (id, text, author_id) VALUES (5, 'Every attempt to do some photos with DSLR are as masochistic session. ', 2);
