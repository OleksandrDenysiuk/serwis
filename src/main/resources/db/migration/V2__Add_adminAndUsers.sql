insert into usr (id, username, password, active, locked)
values (0, 'admin', '1', true, false);

insert into user_role (user_id, roles)
values (0, 'USER'), (0, 'ADMIN');

insert into usr (id, username, password, active, locked)
values (1, 'user', '1', true, false);

insert into user_role (user_id, roles)
values (1, 'USER');

insert into usr (id, username, password, active, locked)
values (2, 'user2', '1', true, false);

insert into user_role (user_id, roles)
values (2, 'USER');

insert into usr (id, username, password, active, locked)
values (3, 'user3', '1', true, false);

insert into user_role (user_id, roles)
values (3, 'USER');