drop table MusikkFremfoertIVerk;
drop table SelskapProduksjon;
drop table ProduksjonForMedia;
drop table ProduksjonSjanger;
drop table Anmeldelse;
drop table RegissoerForVerk;
drop table ManusforfatterForVerk;
drop table SkuespillerIVerk;
drop table Musikk;
drop table Komponist;
drop table Bruker;
drop table Regissoer;
drop table Manusforfatter;
drop table Skuespiller;
drop table Person;
drop table Selskap;
drop table Media;
drop table Sjanger;
drop table Episode;
drop table Sesong;
drop table Serie;
drop table Film;
drop table Produksjon;
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

create table Produksjon(
	verkID integer not null,
    constraint Produksjon_PK primary key (verkID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table Film(
	verkID integer not null,
    constraint Film_PK primary key (verkID),
    constraint foreign key (verkID) references Produksjon(verkID) on delete cascade
);

create table Serie(
	verkID integer not null,
    constraint Serie_PK primary key (verkID),
    constraint foreign key (verkID) references Produksjon(verkID) on delete cascade
);

create table Sesong(
	sesongID integer not null,
    sesongnummer integer,
    serieID integer not null,
    constraint Sesong_PK primary key (sesongID),
    constraint foreign key (serieID) references Serie(verkID) on delete cascade
);

create table Episode(
	verkID integer not null,
    episodenummer integer,
    sesongID integer not null,
    constraint Episode_PK primary key (verkID),
    constraint foreign key (verkID) references Verk(verkID) on delete cascade,
    constraint foreign key (sesongID) references Sesong(sesongID) on delete cascade
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

create table Skuespiller(
	personID integer not null,
    constraint Skuespiller_PK primary key (personID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table Manusforfatter(
	personID integer not null,
    constraint Manusforfatter_PK primary key (personID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table Regissoer(
	personID integer not null,
    constraint Regissoer_PK primary key (personID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table Bruker(
	personID integer not null,
    constraint Bruker_PK primary key (personID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table Komponist(
	personID integer not null,
    constraint Komponist_PK primary key (personID),
    constraint foreign key (personID) references Person(personID) on delete cascade
);

create table Musikk(
	musikkID integer not null,
    navn varchar(50),
    komponistID integer not null,
    constraint Musikk_PK primary key (musikkID),
    constraint foreign key (komponistID) references Komponist(personID) on delete cascade
);

create table SkuespillerIVerk(
	skuespillerID integer not null,
    verkID integer not null,
    rolle varchar(50),
    constraint SkuespillerIVerk_PK primary key (skuespillerID, verkID),
    constraint foreign key (skuespillerID) references Skuespiller(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table ManusforfatterForVerk(
	manusforfatterID integer not null,
    verkID integer not null,
    constraint ManusforfatterForVerk_PK primary key (manusforfatterID, verkID),
    constraint foreign key (manusforfatterID) references Manusforfatter(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table RegissoerForVerk(
	regissoerID integer not null,
    verkID integer not null,
    constraint RegissoerForVerk_PK primary key (regissoerID, verkID),
    constraint foreign key (regissoerID) references Regissoer(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table Anmeldelse(
	brukerID integer not null,
    verkID integer not null,
    raiting integer,
    kommentar varchar(500),
    constraint Anmeldelse_PK primary key (brukerID, verkID),
    constraint foreign key (brukerID) references Bruker(personID) on delete cascade,
    constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

create table ProduksjonSjanger(
	produksjonID integer not null,
	sjangerID integer not null,
    constraint ProduksjonSjanger_PK primary key (produksjonID, sjangerID),
    constraint foreign key (produksjonID) references Produksjon(verkID) on delete cascade,
    constraint foreign key (sjangerID) references Sjanger(sjangerID) on delete cascade
);

create table ProduksjonForMedia(
	produksjonID integer not null,
	mediaID integer not null,
    constraint ProduksjonForMedia_PK primary key (produksjonID, mediaID),
    constraint foreign key (produksjonID) references Produksjon(verkID) on delete cascade,
    constraint foreign key (mediaID) references Media(mediaID) on delete cascade
);

create table SelskapProduksjon(
	selskapID integer not null,
	produksjonID integer not null,
    constraint ProduksjonForMedia_PK primary key (selskapID, produksjonID),
    constraint foreign key (selskapID) references Selskap(selskapID) on delete cascade,
	constraint foreign key (produksjonID) references Produksjon(verkID) on delete cascade
);

create table MusikkFremfoertIVerk(
	musikkID integer not null,
	komponistID integer not null,
	verkID integer not null,
    constraint MusikkFremfoertIVerk_PK primary key (musikkID, komponistID, verkID),
    constraint foreign key (musikkID) references Musikk(musikkID) on delete cascade,
	constraint foreign key (komponistID) references Komponist(personID) on delete cascade,
	constraint foreign key (verkID) references Verk(verkID) on delete cascade
);

