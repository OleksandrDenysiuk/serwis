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

create table file
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

create table rated_user
(
    rated_user_id int8 not null,
    place_id      int8 not null,
    primary key (place_id, rated_user_id)
);

create  table rated_trip_user
(
    rated_user_id int8 not null,
    trip_id      int8 not null,
    primary key (trip_id, rated_user_id)
);

create table subscriber_place
(
    subscriber_id int8 not null,
    place_id      int8 not null,
    primary key (place_id, subscriber_id)
);

create table tag
(
    place_id int8 not null,
    tag      varchar(255)
);

create table trip
(
    id      int8 not null,
    name    varchar(255),
    user_id int8,
    rating      int4 not null,
    description    varchar(255),
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

create table message_type
(
    message_id int8 not null,
    type   varchar(255)
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

create table message
(
    id       int8 not null,
    content     varchar(255),
    author_id  int8,
    user_id  int8,
    place_id int8,
    primary key (id)
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

alter table if exists file
    add constraint file_place_fk
        foreign key (place_id) references place;

alter table if exists place
    add constraint place_user_fk
        foreign key (user_id) references usr;

alter table if exists rated_user
    add constraint user_place_fk
        foreign key (place_id) references place;

alter table if exists rated_user
    add constraint place_user_fk
        foreign key (rated_user_id) references usr;

alter table if exists rated_trip_user
    add constraint user_trip_fk
        foreign key (trip_id) references trip;

alter table if exists rated_trip_user
    add constraint trip_user_fk
        foreign key (rated_user_id) references usr;

alter table if exists subscriber_place
    add constraint subscriber_place_fk
        foreign key (place_id) references place;

alter table if exists subscriber_place
    add constraint place_subscriber_fk
        foreign key (subscriber_id) references usr;

alter table if exists tag
    add constraint tag_place_fk
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

alter table if exists message_type
    add constraint message_type_fk
        foreign key (message_id) references message;

alter table if exists message
    add constraint message_author_fk
        foreign key (author_id) references usr;

alter table if exists message
    add constraint message_user_fk
        foreign key (user_id) references usr;