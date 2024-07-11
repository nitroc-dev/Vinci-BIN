drop table categories;
drop table users;
drop table questions;
drop table answers;

create table categories
(
    id_category integer      not null
        constraint categories_pk
            primary key autoincrement,
    name        varchar not null
);

create unique index categories_name_uindex
    on categories (name);

create table users
(
    id_user    integer      not null
        constraint users_pk
            primary key autoincrement,
    first_name varchar not null,
    last_name  varchar not null,
    email      varchar not null unique,
    password   varchar not null,
    is_admin   boolean default false not null
);

create table questions
(
    id_question   integer      not null
        constraint questions_pk
            primary key autoincrement,
    title         varchar not null,
    subject       text         not null,
    creation_date timestamp default (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')) not null,
    is_reported   boolean   default false not null,
    id_user       integer      not null
        references users
            on update cascade,
    id_category   integer      not null
        references categories
            on update cascade on delete cascade,
    is_solved     boolean   default false not null
);

create table answers
(
    id_answer     integer not null
        constraint answers_pk
            primary key autoincrement,
    question_answer       text    not null,
    creation_date timestamp default (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME')) not null,
    is_reported   boolean   default false not null,
    id_question   integer
        references questions
            on update cascade on delete cascade,
    id_user       integer
        references users
            on update cascade,
    is_correct    boolean   default false not null
);