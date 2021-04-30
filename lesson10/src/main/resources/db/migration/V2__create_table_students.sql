create table students
(
    id         SERIAL primary key ,
    name       text not null,
    surname    text not null,
    faculty_id int
);