insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('undefined , Ukraina',2, 'The mountain is located in the Eastern Beskids, in the Chornohora region. ' ||
                               'The slopes are covered with beech and spruce forests, ' ||
                               'above which there is a belt of sub-alpine ' ||
                               'meadows called polonyna in Ukrainian.',
        '48.16058943132621', '24.49951171875', '0', 'place', 1, '2020-01-03');

insert into files (place_id, file_name) values (1, '4259e73f78f24f3ea9175b06fd8b41c5');

insert into files (place_id, file_name) values (1, 'b63a4cbe2bb94d77be2209a807fcdd7d');

insert into files (place_id, file_name) values (1, 'f15f5c1c78a14f25b6ee740d61e722ec');

insert into tags (place_id, tag) values (1, 'mountain');
insert into tags (place_id, tag) values (1, 'ukraine');
insert into tags (place_id, tag) values (1, 'highest');

insert into subscribers_places (place_id, subscriber_id) values (1, 2);

insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Polska',1, 'Brama Krakowska – XIV-wieczna brama strzegąca dostępu do Starego Miasta w Lublinie, ' ||
                             'historyczny symbol grodu. Pozostałość murów obronnych z XIV wieku; ' ||
                             'zbudowana w stylu gotyckim, w XVIII wieku nadano jej rys barokowy.',
        '51.25676919359793', '22.572612762451172', '0', 'place', 2,'2020-01-03');

insert into files (place_id, file_name) values (2, 'ac39bf11fee44610b02489eafe3ab80f');

insert into files (place_id, file_name) values (2, 'a6bf2d9ba51c42dd8f1d59f60edc81ba');

insert into files (place_id, file_name) values (2, '6f3d9282033b481b8a84438eb703d821');

insert into tags (place_id, tag) values (2, 'brama');
insert into tags (place_id, tag) values (2, 'history');

insert into subscribers_places (place_id, subscriber_id) values (2, 1);

insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Polska',1, 'Obiekt Dom na Podwalu to XVII-wieczny kompleks zabudowań, ' ||
                             'który położony jest na skraju Starego Miasta w Lublinie i ' ||
                             'obejmuje zabytkowy kościół św. Wojciecha. Od zamku dzielą go 2 minuty spacerem.',
        '51.256500615144404', '22.58711814880371', '0', 'sleep', 3,'2020-01-03');

insert into files (place_id, file_name) values (3, 'a9edb152293d45c9b8983a0dc3b94efb');

insert into files (place_id, file_name) values (3, 'a728465afa85490287a8c641185d38b9');

insert into files (place_id, file_name) values (3, 'f84a4524c4e04d49a6547f0c6397b8c9');

insert into tags (place_id, tag) values (3, 'nocleg');
insert into tags (place_id, tag) values (3, 'tanio');
insert into tags (place_id, tag) values (3, 'blisko');

insert into subscribers_places (place_id, subscriber_id) values (3, 1);