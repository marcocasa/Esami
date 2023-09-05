alter table province drop constraint province_pkey;
alter table province drop column gid;
alter table province add constraint province_pkey primary key (id_prov);




CREATE TABLE  decessi
(id_decessi INTEGER primary key,
nome_malattia VARCHAR(100) NOT NULL,
numero_decessi_maschi INTEGER NOT NULL,
numero_decessi_femmine INTEGER NOT NULL,
anno INTEGER NOT NULL,
id_provincia INTEGER NOT NULL,
FOREIGN KEY (id_provincia) REFERENCES province(id_prov)
);






alter table comuni drop constraint comuni_pkey;
alter table comuni drop column gid;
alter table comuni add constraint comuni_pkey primary key (id_comune);



Alter table comuni alter column id_comune type integer;
Alter table comuni alter column id_prov type integer;


ALTER TABLE comuni ADD FOREIGN KEY (id_prov) REFERENCES province(id_prov);


ALTER TABLE comuni 
ALTER COLUMN geom TYPE geometry(MULTIPOLYGON, 4326) USING ST_Transform(ST_SetSRID(geom,32632),4326);


ALTER TABLE province 
ALTER COLUMN geom TYPE geometry(MULTIPOLYGON, 4326) USING ST_Transform(ST_SetSRID(geom,32632),4326);


CREATE TABLE  stazioni
(id_staz INTEGER primary key,
nome VARCHAR(100) NOT NULL,
tipo_zona CHAR(1) NOT NULL,
tipo_staz CHAR(1) NOT NULL,
lat numeric NOT NULL,
long numeric NOT NULL,
id_comune INTEGER not null,
FOREIGN KEY (id_comune) REFERENCES comuni(id_comune)
);



ALTER TABLE stazioni ADD COLUMN geom geometry(Point, 4326);


CREATE TABLE  indicatori
(nome varchar(50) primary key,
sigla VARCHAR(10) NOT NULL,
soglia_giorni_superamento INTEGER NOT NULL,
soglia_media_annuale FLOAT NOT NULL);


CREATE TABLE  rilevazioni
(id_rilevazione integer primary key,
anno integer NOT NULL,
giorni_superamento INTEGER NOT NULL,
media_annuale FLOAT NOT NULL,
massimo Float NOT NULL,
nome_indicatore VARCHAR(50) NOT NULL,
id_stazione INTEGER NOT NULL,
FOREIGN KEY (nome_indicatore) REFERENCES indicatori(nome),
FOREIGN KEY (id_stazione) REFERENCES stazioni(id_staz)
);


CREATE TABLE  rileva
(id_stazione integer,
id_indicatore varchar(50),
primary key(id_stazione, id_indicatore),
FOREIGN KEY (id_indicatore) REFERENCES indicatori(nome),
FOREIGN KEY (id_stazione) REFERENCES stazioni(id_staz)
);


