CREATE TABLE IF NOT EXISTS users
(
    id INTEGER
        CONSTRAINT users_pk
            PRIMARY KEY autoincrement,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    regDate INTEGER DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE communities
(
    id INTEGER
        CONSTRAINT communities_pk
            PRIMARY KEY autoincrement,
    name TEXT NOT NULL,
    membersCount INTEGER NOT NULL DEFAULT 1,
    isClose INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS articles
(
    id INTEGER
        CONSTRAINT articles_pk
            PRIMARY KEY autoincrement,
    name TEXT NOT NULL,
    authorId INTEGER NOUT NULL
        CONSTRAINT articles_author
            REFERENCES users
            ON DELETE CASCADE,
    publicationTime INTEGER DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS community_admins
(
    user_id INTEGER
        CONSTRAINT community_admins_users
            REFERENCES users
            ON DELETE CASCADE,
    article_id INTEGER
        CONSTRAINT community_admins_community
            REFERENCES articles
            ON DELETE CASCADE
);

