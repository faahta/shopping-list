create table item
(
    id        integer not null
        constraint item_pk
            primary key,
    item_name integer,
    price     numeric
);

alter table item
    owner to postgres;

create table category
(
    id            integer not null
        constraint category_pk
            primary key,
    category_name varchar(100)
);

alter table category
    owner to postgres;

create table users
(
    id        integer not null
        constraint users_pk
            primary key,
    email     varchar(100),
    full_name varchar(100),
    password  varchar(100)
);

alter table users
    owner to postgres;

create table shopping_list
(
    id        integer,
    list_name varchar(50)
);

alter table shopping_list
    owner to postgres;

create table shopping_list_item
(
    id      integer not null
        constraint shopping_list_item_pk
            primary key,
    list_id integer,
    item_id integer
);

alter table shopping_list_item
    owner to postgres;

create table confirmation_token
(
    token_id           integer not null
        constraint confirmation_token_pk
            primary key,
    confirmation_token varchar(200),
    created_date       date,
    user_id            integer
);

alter table confirmation_token
    owner to postgres;

