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

INSERT INTO categories(name) VALUES ('JavaScript');
INSERT INTO categories(name) VALUES ('PHP');

INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES ('o', 'ch', 'o.ch@vinci.be', '$2a$10$Pb8Ds8.2uA.WR0M9PISPQ.kM366tfes68tEOJlF/myWTPYguSAqi.', false);
INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES ('s', 'eb', 's.eb@vinci.be', '$2a$10$Pb8Ds8.2uA.WR0M9PISPQ.kM366tfes68tEOJlF/myWTPYguSAqi.', false);
INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES ('s', 'tef', 's.tef@vinci.be', '$2a$10$Pb8Ds8.2uA.WR0M9PISPQ.kM366tfes68tEOJlF/myWTPYguSAqi.', true);
INSERT INTO users(first_name, last_name, email, password, is_admin) VALUES ('l', 'le', 'l.le@vinci.be', '$2a$10$Pb8Ds8.2uA.WR0M9PISPQ.kM366tfes68tEOJlF/myWTPYguSAqi.', false);

INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('Error not found', 'J''ai une erreur Not Found quand je vais sur http://localhost:3000/', 1, 1, '2022-05-06 09:00:00', false, false);
INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('module not found', 'J''ai une erreur quand je  fais un npm ""start, la catégorie est"" JavaScript', 1, 3, '2022-05-05 09:00:00', false, false);
INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('Php property error', 'Error accessing object property $id', 2, 3, '2022-05-04 09:00:00', false, false);
INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('Php report', 'fu... PHP', 2, 1, '2022-05-03 09:00:00', true, false);
INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('View not found', 'Je n''arrive pas à afficher ma vue index.js', 1, 3, '2022-05-02 09:00:00', false, true);
INSERT INTO questions(title, subject, id_category, id_user, creation_date, is_reported, is_solved) VALUES ('Js report', 'Comment débugger en JS', 1, 4, '2022-04-30 09:00:00', true, false);

INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('tu dois créer un routeur', 5, 1, '2022-05-03 09:00:00', false, false);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('tu dois vérifier l''existence du fichier vue et ainsi que son chemin', 5, 4, '2022-05-04 09:00:00', true, false);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('fu.. Php 1', 3, 1, '2022-05-05 09:00:00', false, true);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('fu.. Php 2', 3, 1, '2022-05-06 09:00:00', false, true);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('Il faut désérialiser l''objet', 3, 1, '2022-05-07 09:00:00', false, true);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('Php report réponse 1', 4, 3, '2022-05-06 09:00:00', false, false);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('Php report réponse 2', 4, 3, '2022-05-07 09:00:00', false, false);
INSERT INTO answers(question_answer, id_question, id_user, creation_date, is_correct, is_reported) VALUES ('Error not found réponse', 1, 1, '2022-05-07 09:00:00', false, false);