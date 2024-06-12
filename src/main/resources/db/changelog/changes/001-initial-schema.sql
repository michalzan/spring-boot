create table app_user
(
    id     varchar(255) not null
        primary key,
    email  varchar(255),
    name   varchar(255) not null,
    phone  varchar(255),
    city   varchar(255),
    state  varchar(255),
    street varchar(255),
    zip    varchar(255)
);

alter table app_user
    owner to postgres;

create table item
(
    id     varchar(255) not null
        primary key,
    amount bigint
        constraint item_amount_check
            check (amount >= 0),
    name   varchar(255) not null,
    unit   varchar(255)
        constraint item_unit_check
            check ((unit)::text = ANY ((ARRAY ['PC'::character varying, 'KG'::character varying])::text[]))
);

alter table item
    owner to postgres;

create table shop
(
    id     varchar(255) not null
        primary key,
    name   varchar(255) not null,
    city   varchar(255),
    state  varchar(255),
    street varchar(255),
    zip    varchar(255)
);

alter table shop
    owner to postgres;

create table employee
(
    id               varchar(255) not null
        primary key,
    city             varchar(255),
    state            varchar(255),
    street           varchar(255),
    zip              varchar(255),
    email            varchar(255),
    name             varchar(255) not null,
    phone            varchar(255),
    place_of_work_id varchar(255)
        constraint fk88g55e2i3kxubvqf6wqvpoqr5
            references shop,
    salary           bigint
        constraint employee_salary_check
            check (salary >= 0)
);

alter table employee
    owner to postgres;

create table wishlist
(
    user_id varchar(255) not null
        constraint fk_user_id
            references app_user,
    item_id varchar(255) not null
        constraint fkarf2utv20ouckeev3lxt2nixx
            references item
);

alter table wishlist
    owner to postgres;

