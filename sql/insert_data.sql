begin;
insert into Verk values (1, "Star Wars IV: A New Hope", 121, 1977, "1977-12-06", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.");
insert into Produksjon values(1);
insert into Film values(1);
commit;

begin;
insert into Verk values (2, "Star Wars V: The Empire Strikes Back", 124, 1980, "1980-08-10", "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.");
insert into Produksjon values(2);
insert into Film values(2);
commit;

begin;
insert into Verk values (3, "Star Wars VI: Return of the Jedi", 131, 1983, "1983-09-16", "After a daring mission to rescue Han Solo from Jabba the Hutt, the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, Luke struggles to help Darth Vader back from the dark side without falling into the Emperor's trap.");
insert into Produksjon values(3);
insert into Film values(3);
commit;


begin;
insert into Person values(1, "Mark Hamill", 1951, "USA");
insert into Skuespiller values(1);
insert into SkuespillerIVerk values(1, 1, "Luke Skywalker");
insert into SkuespillerIVerk values(1, 2, "Luke Skywalker");
insert into SkuespillerIVerk values(1, 3, "Luke Skywalker");
commit;

begin;
insert into Person values(2, "Harrison Ford", 1942, "USA");
insert into Skuespiller values(2);
insert into SkuespillerIVerk values(2, 1, "Han Solo");
insert into SkuespillerIVerk values(2, 2, "Han Solo");
insert into SkuespillerIVerk values(2, 3, "Han Solo");
commit;

begin;
insert into Person values(3, "Carrie Fisher", 1956, "USA");
insert into Skuespiller values(3);
insert into SkuespillerIVerk values(3, 1, "Princess Leia");
insert into SkuespillerIVerk values(3, 2, "Princess Leia");
insert into SkuespillerIVerk values(3, 3, "Princess Leia");
commit;

begin;
insert into Person values(4, "Lawrence Kasdan", 1949, "USA");
insert into Manusforfatter values(4);
insert into ManusforfatterForVerk values(4, 2);
insert into ManusforfatterForVerk values(4, 3);
commit;

begin;
insert into Person values(5, "George Lucas", 1944, "USA");
insert into Manusforfatter values(5);
insert into Regissoer values(5);
insert into ManusforfatterForVerk values(5, 1);
insert into RegissoerForVerk values(5, 1);
commit;

start transaction;
insert into Sjanger values (1, "Action"), (2, "Adventure"), (3, "Fantasy"), (4, "Sci-Fi"), (5, "Kommedie");
insert into produksjonsjanger values (1, 1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2), (2, 3), (2, 4), (3, 1), (3, 2), (3, 3), (3, 4);
commit;

start transaction;
insert into Media values (1, "TV"), (2, "Streaming"), (3, "Kino");
insert into produksjonformedia values (1, 3), (2, 3), (3, 3);
commit;

start transaction;
insert into Selskap values (1, "Lucasfilm", null, "USA"), (2, "Twentieth Century Fox", null, "USA");
insert into selskapproduksjon values (1, 1), (2, 1), (1, 2), (2, 2), (1, 3), (2, 3);
commit;

start transaction;
insert into Person values (6, "hmorales", null, "USA");
insert into Bruker values (6);
insert into Anmeldelse values(6, 1, 10, "In respect to the many kids of the seventies. I rated this movie as one of the greatest movies ever made.");
commit;

start transaction;
insert into Verk values (4, "Breaking Bad", null, 2008, null, "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.");
insert into Produksjon values (4);
insert into Serie values (4);
insert into Sesong values(1, 1, 4);
insert into Verk values 
	(5, "Pilot", 58, 2008, "2008-01-20", "Diagnosed with terminal lung cancer, chemistry teacher Walter White teams up with former student Jesse Pinkman to cook and sell crystal meth."),
    (6, "Cat's in the Bag...", 48, 2008, "2008-01-27", "After their first drug deal goes terribly wrong, Walt and Jesse are forced to deal with a corpse and a prisoner. Meanwhile, Skyler grows suspicious of Walt's activities.");
insert into Episode values
	(5, 1, 1),
    (6, 2, 1);
insert into Person values
	(7, "Vince Gilligan", 1967, "USA"),
    (8, "Bryan Cranston", 1956, "USA"),
    (9, "Aaron Paul", 1979, "USA"),
    (10, "Anna Gunn", 1968, "USA");
insert into Regissoer values (7);
insert into Manusforfatter values (7);
insert into Skuespiller values (8), (9), (10);
insert into SkuespillerIVerk values
	(8, 4, "Walter White"),
    (9, 4, "Jessie Pinkman"),
    (10, 4, "Skyler White");
commit;

