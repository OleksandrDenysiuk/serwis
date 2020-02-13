-- Place 1
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'Among the oldest royal seats in Poland, Lublin Castle was founded on its namesake hill in the 12th century by the High Duke Casimir II the Just.',
        '51.25047330133449', '22.57244110107422', '0', 'place', 1, '2020-01-15 13:57:35.418000');

insert into file (place_id, file_name)
values (1, '227fd44478474df087034b83988b75cd');

insert into file (place_id, file_name)
values (1, '45564ecfd8ee4e8d983ff5a7758e34e8');

insert into file (place_id, file_name)
values (1, '164e51a177104aadab5df1e9e24aef52');

insert into tag (place_id, tag)
values (1, 'lublin');
insert into tag (place_id, tag)
values (1, 'Castle');
insert into tag (place_id, tag)
values (1, 'oldest');

insert into subscriber_place (place_id, subscriber_id)
values (1, 1);

-- Place 2
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'In the castle’s courtyard is a chapel that would have been raised at the same time as the keep, and was then remodelled at the beginning of the 15th century at the behest of King Władysław Jagiełło.',
        '51.25031549007551', '22.57314383983612', '0', 'place', 2, '2020-01-15 14:08:26.765000');

insert into file (place_id, file_name)
values (2, '5fb7e45ce19344288e684cbce11f3be0');

insert into file (place_id, file_name)
values (2, 'ee490d6838b848e5af9d0f7ecc5451a5');

insert into file (place_id, file_name)
values (2, '87b1f36474694cc7960b2ec4c57bdf09');

insert into tag (place_id, tag)
values (2, 'chapel');
insert into tag (place_id, tag)
values (2, 'Holy Trinity');

insert into subscriber_place (place_id, subscriber_id)
values (2, 1);

-- Place 3
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'No sooner was it liberated by the Soviet army in 1944, this death camp in the southeast of Lublin became a museum, and by 1965 was elevated to become Poland’s first State Museum.',
        '51.22537786735172', '22.60608673095703', '0', 'place', 3, '2020-01-15 14:16:29.365000');

insert into file (place_id, file_name)
values (3, '615ccdc9f0de4de19023d6ff559640a4');

insert into file (place_id, file_name)
values (3, '16992008d3324c1fadd0a7f9dff2e473');

insert into file (place_id, file_name)
values (3, 'a6bef0b1d57e4f02af37411a53fc4a81');

insert into file (place_id, file_name)
values (3, '7a8b3796d5494286a0a8204e6a17d299');

insert into tag (place_id, tag)
values (3, 'murdered');
insert into tag (place_id, tag)
values (3, 'museum');
insert into tag (place_id, tag)
values (3, 'death camp');

insert into subscriber_place (place_id, subscriber_id)
values (3, 1);

-- Place 4
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'Between the castle in the north and the Rynek (Market Square) in the south, Lublin’s Old Town is in a small but endearing package.',
        '51.247797824258924', '22.568836212158203', '0', 'place', 4, '2020-01-15 14:45:01.335000');

insert into file (place_id, file_name)
values (4, '766aa54094704072aa8bdf92b16cfc96');

insert into file (place_id, file_name)
values (4, '177eb60fb44c4b13861cf428f8946b6e');

insert into file (place_id, file_name)
values (4, 'cf5c0df4d48b4f7793a26d0b319dc931');

insert into tag (place_id, tag)
values (4, 'old');
insert into tag (place_id, tag)
values (4, 'town');

insert into subscriber_place (place_id, subscriber_id)
values (4, 1);


-- Place 5
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'Formerly part of a city gate beside Lublin’s Jesuit College, this tower was extended to 60 metres by the Poland-based Italian architect Antonio Corazzi in 1819.',
        '51.247048339582605', '22.568053007125854', '0', 'place', 5, '2020-01-19 20:20:36.444000');

insert into file (place_id, file_name)
values (5, '1124da02a2fe4fab90d4ee8d99bfc002');

insert into file (place_id, file_name)
values (5, '2b214ac6b80f4776afe6567cf3743ac4');

insert into file (place_id, file_name)
values (5, 'e86b18b43c084d4d8b0deaab87a71550');

insert into tag (place_id, tag)
values (5, 'Trinity');
insert into tag (place_id, tag)
values (5, 'tower');

insert into subscriber_place (place_id, subscriber_id)
values (5, 1);

-- Place 6
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'The solemn Neoclassical building in the centre of the Rynek is Lublin’s old Crown Tribunal, which functioned from 1578 to 1794.',
        '51.2477694495425', '22.567969858646393', '0', 'place', 6, '2020-01-19 20:25:33.418000');

insert into file (place_id, file_name)
values (5, '894f878ca2514023b5e13f9936b5b2c2');

insert into file (place_id, file_name)
values (6, 'b5a68b7ddf124afc9fbbf246c796ff59');

insert into file (place_id, file_name)
values (6, 'a8da1177da614a73afb23839d8d258e2');

insert into tag (place_id, tag)
values (6, 'Crown');
insert into tag (place_id, tag)
values (6, 'Tribunal');

insert into subscriber_place (place_id, subscriber_id)
values (6, 1);

-- Place 7
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'Conveniently located between the Rynek and the Grodzka Gate, Po Farze Square is a go-to meeting point for Lulin’s residents.',
        '51.24876085234369', '22.569286823272705', '0', 'place', 7, '2020-01-19 20:28:52.759000');

insert into file (place_id, file_name)
values (7, 'd4e3f58eca234e08857eada97fdd03e0');

insert into file (place_id, file_name)
values (7, '542e7e6dc33141f28b118c9af172c6a0');

insert into tag (place_id, tag)
values (7, 'Square');

insert into subscriber_place (place_id, subscriber_id)
values (7, 1);

-- Place 8
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'On the north slope of the Czechówka River this botanical garden was first laid out in 1956 and encompasses 25 hectares.',
        '51.26575492344705', '22.516651153564453', '0', 'place', 8, '2020-01-19 20:32:12.319000');

insert into file (place_id, file_name)
values (8, 'a584b6f3603942b7a06d9c6cc27231f9');

insert into file (place_id, file_name)
values (8, '6938be6dcf7040e595fe84156531d5b2');

insert into file (place_id, file_name)
values (8, 'a1cb16d3cfcd40108e6cd6ed2883e632');

insert into tag (place_id, tag)
values (8, 'Maria');
insert into tag (place_id, tag)
values (8, 'Curie-Skłodowska');
insert into tag (place_id, tag)
values (8, 'Botanical Garden');

insert into subscriber_place (place_id, subscriber_id)
values (8, 1);

-- Place 9
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'The gate also once marked the entrance to the Jewish quarter of Lublin, and over the last couple of decades has been converted into a cultural centre for Lublin’s Polish-Jewish heritage.',
        '51.249586865112335', '22.56987690925598', '0', 'place', 9, '2020-01-19 20:39:42.345000');

insert into file (place_id, file_name)
values (9, '774488d8504b466db3b067ec7ff3217e');

insert into file (place_id, file_name)
values (9, 'f2e2a096c77a4d84a0ca5f9e49e775fa');

insert into file (place_id, file_name)
values (9, '802725cf137449c88d9c6273df61c81e');

insert into tag (place_id, tag)
values (9, 'Grodzka');
insert into tag (place_id, tag)
values (9, 'Gate');
insert into tag (place_id, tag)
values (9, 'Grodzka');

insert into subscriber_place (place_id, subscriber_id)
values (9, 1);

-- Place 10
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'After entering the dungeons at the Crown Tribunal you can set off on a Goonies-style adventure through the interlinked cellars of Lublin’s 16th and 17th-century townhouses.',
        '51.247760215377', '22.567843794822693', '0', 'place', 10, '2020-01-19 20:43:04.332000');

insert into file (place_id, file_name)
values (10, 'b5b40b5bad7e430fa463f1d688ca04f6');

insert into file (place_id, file_name)
values (10, 'b26aedb4da6c4911a29ce8a022f7e1eb');

insert into file (place_id, file_name)
values (10, '1885f92ee7fe412282e360f23c559166');

insert into tag (place_id, tag)
values (10, 'Underground');
insert into tag (place_id, tag)
values (10, 'Trail');
insert into tag (place_id, tag)
values (10, 'Lublin');

insert into subscriber_place (place_id, subscriber_id)
values (10, 1);

-- Place 11
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'No need to check street numbers, just look for the permanent queue in front of this ice-cream shop that trumpets the provenance of its treats. Everything is made in-house and the flavours change with the seasons.',
        '51.247404278857225', '22.565349340438843', '0', 'food', 11, '2020-01-19 20:56:20.158000');

insert into file (place_id, file_name)
values (11, 'a1fc653af140428ba178b0ac5a9a8d4e');

insert into file (place_id, file_name)
values (11, '90f1907163694d7e8e72ef63c5224a15');

insert into file (place_id, file_name)
values (11, '8c580fb5530c4d72b2cc4e8acadcff3c');

insert into tag (place_id, tag)
values (11, 'Ice Cream');
insert into tag (place_id, tag)
values (11, 'Lublin');

insert into subscriber_place (place_id, subscriber_id)
values (11, 1);

-- Place 12
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'This fine restaurant is in a lush cellar on the main street. The menu is a mix of international staples along with Polish favourites such as żurek (sour rye soup), roast duck served with beetroot and a fine bevy of regional specialities like goose fillet.',
        '51.24801877131017', '22.55693793296814', '0', 'food', 12, '2020-01-19 20:58:48.228000');

insert into file (place_id, file_name)
values (12, 'd229571ec0fb4ff1908a3ecc1902625a');

insert into file (place_id, file_name)
values (12, '0629b2ff02824933aac4940f589ddd67');

insert into file (place_id, file_name)
values (12, '348ae51fda3d4778b1aa810988785487');

insert into tag (place_id, tag)
values (12, 'Kardamon');

insert into subscriber_place (place_id, subscriber_id)
values (12, 1);

-- Place 13
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'There’s good kitsch and there’s bad kitsch, and at Mandragora, it’s all good. Sure, ''Hava Nagila'' is playing amid the lace tablecloths, knick-knacks and photos of old Lublin, but in the romantic Rynek setting it works wonderfully. The food is heartily Jew',
        '51.24790124606622', '22.568503618240356', '0', 'food', 13, '2020-01-19 21:01:01.871000');

insert into file (place_id, file_name)
values (13, 'a204ceea59d147ee93848f32d67ee6b7');

insert into file (place_id, file_name)
values (13, '30b72a25fd7541709cc54a82e6b32f1d');

insert into file (place_id, file_name)
values (13, 'd81243e30dfb4cdd8ec95efc464fc8d1');

insert into tag (place_id, tag)
values (13, 'Mandragora');

insert into subscriber_place (place_id, subscriber_id)
values (13, 1);

-- Place 14
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'Hotel In Between by Vanilla jest usytuowany w spokojnej okolicy, w odległości 8 km od centrum Lublina. Obiekt oferuje nowoczesną restaurację oraz zaplecze konferencyjno-bankietowe o powierzchni 900 m². W całym budynku można korzystać z bezpłatnego WiFi.',
        '51.29276061719161', '22.453179359436035', '0', 'sleep', 14, '2020-01-19 21:04:24.543000');

insert into file (place_id, file_name)
values (14, '527b8b90322247e4be1c6a1307d1f200');

insert into file (place_id, file_name)
values (14, 'f12f67f085d4454e8e8b1e9a6fc18d32');

insert into file (place_id, file_name)
values (14, '06455dc45e694c5baf08fea0eeb1ceaa');

insert into tag (place_id, tag)
values (14, 'Hotel');
insert into tag (place_id, tag)
values (14, 'In Between');
insert into tag (place_id, tag)
values (14, 'by Vanilla');

insert into subscriber_place (place_id, subscriber_id)
values (14, 1);

-- Place 15
insert into place (address, user_id, description, latitude, longitude, rating, type, id, date)
values ('Lublin , Poland', 1,
        'One of our top picks in Lublin.Located in Lublin, 3.5 km from Sobieski Family Palace, Lubhotel provides accommodations with a restaurant, free private parking, a bar and a terrace.',
        '51.232146787645114', '22.592225074768066', '0', 'sleep', 15, '2020-01-19 21:07:18.981000');

insert into file (place_id, file_name)
values (15, '6840113838f24a80a6a8509707966272');

insert into file (place_id, file_name)
values (15, '42942483d81749cdb2986f15358ec260');

insert into file (place_id, file_name)
values (15, '5f4c2410da1b403b82bc8462c0ec6120');

insert into tag (place_id, tag)
values (15, 'Lubhotel');

insert into subscriber_place (place_id, subscriber_id)
values (15, 1);

-- trip 1
insert into trip (user_id, description, name, rating, id)
values (1,
        'The greatest empire in Renaissance Europe was born in Lublin in Lesser Poland in 1569. The Union of Lublin created the Polish-Lithuanian Commonwealth, and Lublin, on the old road from Kraków to Vilnius, was the place where the document was signed.',
        'Lublin', '4', 1);

insert into trip_place (trip_id, place_id) values (1, 1);
insert into trip_place (trip_id, place_id) values (1, 2);
insert into trip_place (trip_id, place_id) values (1, 3);
insert into trip_place (trip_id, place_id) values (1, 4);
insert into trip_place (trip_id, place_id) values (1, 5);
insert into trip_place (trip_id, place_id) values (1, 11);
insert into trip_place (trip_id, place_id) values (1, 12);
insert into trip_place (trip_id, place_id) values (1, 14);

-- trip 2
insert into trip (user_id, description, name, rating, id)
values (1,
        'The greatest empire in Renaissance Europe was born in Lublin in Lesser Poland in 1569. The Union of Lublin created the Polish-Lithuanian Commonwealth, and Lublin, on the old road from Kraków to Vilnius, was the place where the document was signed.',
        'Lublin', '4', 2);

insert into trip_place (trip_id, place_id) values (2, 6);
insert into trip_place (trip_id, place_id) values (2, 7);
insert into trip_place (trip_id, place_id) values (2, 8);
insert into trip_place (trip_id, place_id) values (2, 9);
insert into trip_place (trip_id, place_id) values (2, 10);
insert into trip_place (trip_id, place_id) values (2, 12);
insert into trip_place (trip_id, place_id) values (2, 13);
insert into trip_place (trip_id, place_id) values (2, 15);

