drop table MusikkFremfoertIVerk;
drop table SelskapVerk;
drop table VerkForMedia;
drop table VerkSjanger;
drop table Anmeldelse;
drop table AnsattIVerk;
drop table SkuespillerIVerk;
drop table Musikk;
drop table Bruker;
drop table Person;
drop table Selskap;
drop table Media;
drop table Sjanger;
drop table Episode;
drop table Serie;
drop table Film;
drop table Verk;

create table Verk(
	verkID integer not null,
    tittel varchar(50),
    lengde integer,
    utgivelsesaar integer,
    lanseringsdato date,
    innhold varchar(500),
    constraint primary key (verkID)
);

create table Film(
	verkID integer not null,
    constraint Film_PK primary key (verkID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table Serie(
	verkID integer not null,
    constraint Serie_PK primary key (verkID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table Episode(
	verkID integer not null,
    sessongnummer integer,
    episodenummer integer,
    serieID integer not null,
    constraint Episode_PK primary key (verkID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade,
    constraint foreign key (serieID) references Serie(verkID) on delete cascade
);

create table Sjanger(
	sjangerID integer not null,
    navn varchar(50),
    constraint Sjanger_PK primary key (sjangerID)
);

create table Media(
	mediaID integer not null,
    navn varchar(50),
    constraint Media_PK primary key (mediaID)
);

create table Selskap(
	selskapID integer not null,
    adresse varchar(50),
    url varchar(50),
    land varchar(50),
    constraint Selskap_PK primary key (selskapID)
);

create table Person(
	personID integer not null,
    navn varchar(50),
    foedselsaar integer,
    foedselsland varchar(50),
    constraint Person_PK primary key (personID)
);

create table Bruker(
	brukerID integer not null,
    brukernavn varchar(50),
    epost varchar(50),
    constraint Bruker_PK primary key (brukerID)
);

create table Musikk(
	musikkID integer not null,
    navn varchar(50),
    personID integer not null,
    constraint Musikk_PK primary key (musikkID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table SkuespillerIVerk(
	personID integer not null,
    verkID integer not null,
    rolle varchar(50),
    constraint SkuespillerIVerk_PK primary key (personID, verkID),
    constraint foreign key (personID) references Person(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table AnsattIVerk(
	personID integer not null,
    verkID integer not null,
    ansattType varchar(30) not null,
    constraint AnsattIVerk_PK primary key (personID, verkID, ansattType),
    constraint foreign key (personID) references Person(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table Anmeldelse(
	brukerID integer not null,
    verkID integer not null,
    raiting integer,
    kommentar varchar(500),
    constraint Anmeldelse_PK primary key (brukerID, verkID),
    constraint foreign key (brukerID) references Bruker(brukerID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table VerkSjanger(
	verkID integer not null,
	sjangerID integer not null,
    constraint VerkSjanger_PK primary key (verkID, sjangerID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade,
    constraint foreign key (sjangerID) references Sjanger(sjangerID) on delete cascade
);

create table VerkForMedia(
	verkID integer not null,
	mediaID integer not null,
    constraint Media_PK primary key (verkID, mediaID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade,
    constraint foreign key (mediaID) references Media(mediaID) on delete cascade
);

create table SelskapVerk(
	selskapID integer not null,
	verkID integer not null,
    constraint SelskapVerk_PK primary key (selskapID, verkID),
    constraint foreign key (selskapID) references Selskap(selskapID) on delete cascade,
	constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table MusikkFremfoertIVerk(
	musikkID integer not null,
	personID integer not null,
	verkID integer not null,
    constraint MusikkFremfoertIVerk_PK primary key (musikkID, personID, verkID),
    constraint foreign key (musikkID) references Musikk(musikkID) on delete cascade,
	constraint foreign key (personID) references Person(personID) on delete cascade,
	constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

