create table users
(
    id    serial not null
        constraint user_pk
            primary key,
    name  text   not null,
    email text   not null
);

alter table users
    owner to pgusr;

INSERT INTO users (id, name, email) VALUES (1, 'Albert Akmukhametov', 'albert@kinjalik.ru');
INSERT INTO users (id, name, email) VALUES (2, 'Albert Akmukhametov', 'a.akmukhametov@innopolis.university');
INSERT INTO users (id, name, email) VALUES (3, 'Ivan Ivanov', 'ivanov@kinjalik.ru');
