create sequence hibernate_sequence start 1 increment 1;

create table comment
(
    id       int8 not null,
    content  varchar(255),
    user_id  int8,
    trip_id  int8,
    place_id int8,
    primary key (id)
);

create table files
(
    place_id  int8 not null,
    file_name varchar(255)
);

create table place
(
    id          int8 not null,
    address     varchar(255),
    description varchar(255),
    latitude    varchar(255),
    longitude   varchar(255),
    rating      int4 not null,
    type        varchar(255),
    user_id     int8,
    date timestamp,
    primary key (id)
);

create table rated_users
(
    rated_user_id int8 not null,
    place_id      int8 not null,
    primary key (place_id, rated_user_id)
);

create table subscribers_places
(
    subscriber_id int8 not null,
    place_id      int8 not null,
    primary key (place_id, subscriber_id)
);

create table tags
(
    place_id int8 not null,
    tag      varchar(255)
);

create table trip
(
    id      int8 not null,
    name    varchar(255),
    user_id int8,
    primary key (id)
);

create table trip_place
(
    trip_id  int8 not null,
    place_id int8 not null,
    primary key (trip_id, place_id)
);

create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id       int8    not null,
    active   boolean not null,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    surname  varchar(255),
    username varchar(255),
    locked   boolean not null,
    primary key (id)
);

create table ban
(
    id       int8 not null,
    type     varchar(255),
    user_id  int8,
    place_id int8,
    primary key (id)
);

create table messages
(
    user_id int8 not null,
    message varchar(255)
);

alter table if exists comment
    add constraint comment_user_fk
        foreign key (user_id) references usr;


alter table if exists comment
    add constraint comment_trip_fk
        foreign key (trip_id) references trip;

alter table if exists comment
    add constraint comment_place_fk
        foreign key (place_id) references place;

alter table if exists files
    add constraint files_place_fk
        foreign key (place_id) references place;

alter table if exists place
    add constraint place_user_fk
        foreign key (user_id) references usr;

alter table if exists rated_users
    add constraint users_place_fk
        foreign key (place_id) references place;

alter table if exists rated_users
    add constraint place_users_fk
        foreign key (rated_user_id) references usr;

alter table if exists subscribers_places
    add constraint subscribers_place_fk
        foreign key (place_id) references place;

alter table if exists subscribers_places
    add constraint places_subscriber_fk
        foreign key (subscriber_id) references usr;

alter table if exists tags
    add constraint tags_place_fk
        foreign key (place_id) references place;

alter table if exists trip
    add constraint trip_user_fk
        foreign key (user_id) references usr;

alter table if exists trip_place
    add constraint trip_place_fk
        foreign key (place_id) references place;

alter table if exists trip_place
    add constraint place_trip_fk
        foreign key (trip_id) references trip;

alter table if exists user_role
    add constraint user_role_fk
        foreign key (user_id) references usr;

alter table if exists messages
    add constraint messages_user_fk
        foreign key (user_id) references usr;