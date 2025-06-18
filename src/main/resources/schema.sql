drop table if exists artists;

drop table if exists songs;

drop table if exists users;

drop table if exists oauth_refresh_token;

drop table if exists oauth_access_token;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table artists
(
    id   bigint not null
        constraint artists_pkey
            primary key,
    name varchar(255)
);

alter table artists owner to postgres;


create table songs
(
    id         bigint not null
        constraint songs_pkey
            primary key,
    album      varchar(255),
    artist     varchar(255),
    bpm        bigint,
    duration   bigint,
    genre      varchar(255),
    name       varchar(255),
    short_name varchar(255),
    spotify_id varchar(255),
    year       integer
);

alter table songs owner to postgres;

create table users
(
    email    varchar(255) not null
        constraint users_pkey
            primary key,
    date     timestamp,
    name     varchar(255),
    password varchar(255),
    role     varchar(255)
);

alter table users owner to postgres;

create table oauth_access_token
(
    authentication_id VARCHAR(256),
    token_id          VARCHAR(256),
    token             bytea,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    bytea,
    refresh_token     VARCHAR(256)
);

alter table oauth_access_token owner to postgres;

create table oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

alter table oauth_refresh_token owner to postgres;

delete
from songs;

delete
from artists;

delete
from users;

INSERT INTO users (email, date, name, password, role)
VALUES ('jofisaes@gmail.com', null, 'Joao', '$2a$10$4Ocr65g2xbde4K1NC9j19.OV9CTfCRX1NG9pQngSyX8wtHJs2TvBq', 'MASTER');
