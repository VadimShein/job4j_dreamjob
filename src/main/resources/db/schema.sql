create table if not exists users(
    id serial primary key,
    name varchar not null,
    email varchar unique not null,
    password varchar not null
);

create table if not exists post(
    id serial primary key,
    name varchar not null,
    description varchar not null,
    created timestamp not null
);

create table if not exists cities(
    id   serial primary key,
    city varchar not null
);

create table if not exists candidate(
    id serial primary key,
    name varchar not null,
    description varchar not null,
    created timestamp not null,
    cityid int
);

