insert into usr (id, username, password, active, locked)
values (0, 'admin', '1', true, false);

insert into user_role (user_id, roles)
values (0, 'USER'), (0, 'ADMIN');

insert into usr (id, username, password, active, locked,email,name,surname)
values (1, 'helgi', '1', true, false,'oleh@gmail.com','Oleh','Chycherskyi');

insert into user_role (user_id, roles)
values (1, 'USER');

insert into usr (id, username, password, active, locked,email,name,surname)
values (2, 'gazon', '1', true, false,'gazon@gmail.com','Denys','Chornyi');

insert into user_role (user_id, roles)
values (2, 'USER');

insert into usr (id, username, password, active, locked,email,name,surname)
values (3, 'senyore', '1', true, false,'alex@gmail.com','Oleksandr','Denysiuk');

insert into user_role (user_id, roles)
values (3, 'USER');