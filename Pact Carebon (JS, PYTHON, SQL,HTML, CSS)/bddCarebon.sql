BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Produits_consommés_par_Utilisateurs" (
	"Id_conso" INTEGER,
	"Id_produit"    INTEGER,
	"Id_user" INTEGER,
	"Masse_g"       INTEGER,
	"CO2" REAL,
	"Date" DATE,
	PRIMARY KEY ("Id_conso")
);

CREATE TABLE IF NOT EXISTS "BDD_appareils_ménagers" (
	"Id_appareil"   INTEGER,
	"Nom_appreil"   TEXT,
	"KgCo2an"       REAL
);

CREATE TABLE IF NOT EXISTS "Trajets" (
	"Id_trajet" INTEGER,
	"Id_user"       INTEGER,
	"Id_moyen_de_transport" INTEGER,
	"Distance" REAL,
	"CO2" REAL,
	"Date"  DATE,
	PRIMARY KEY ("Id_trajet")
);

CREATE TABLE IF NOT EXISTS "BDD_Consommation_Moyens_de_transport" (
	"Id_moyen_de_transport" INTEGER,
	"Nom_Moyen_de_transport"        TEXT,
	"kgCo2km"       REAL
);

CREATE TABLE IF NOT EXISTS "Custom_Action" (
	"Id_action" INTEGER,
	"Id_user" INTEGER,
	"Message"       TEXT,
	"CO2"   REAL,
	"Date" DATE,
	PRIMARY KEY ("Id_action")
);

CREATE TABLE IF NOT EXISTS "Utilisateurs" (
	"Id_user"       INTEGER,
	"Admin_user"    INTEGER,
	"Username"      TEXT UNIQUE,
	"user_Password" TEXT,
	"mail"  TEXT UNIQUE,
	"firstName"     TEXT,
	"lastName"      TEXT,
	"country"       TEXT,
	"birth" TEXT,
	"mobile"        TEXT,
	"friends_list" TEXT DEFAULT '',
	"friends_list_on_wait" TEXT DEFAULT '',
	"friends_list_demands" TEXT DEFAULT '',
	PRIMARY KEY("Id_user")
);

CREATE TABLE IF NOT EXISTS "Total_produits"(
	"id" INTEGER PRIMARY KEY AUTOINCREMENT,
	"Id_user" INTEGER,
	"Date" DATE,
	"CO2" REAL
);

CREATE TABLE IF NOT EXISTS "Total_trajets"(
	"id" INTEGER PRIMARY KEY AUTOINCREMENT,
	"Id_user" INTEGER,
	"Date" DATE,
	"CO2" REAL
);

INSERT INTO "BDD_appareils_ménagers" ("Id_appareil","Nom_appreil","KgCo2an") VALUES (0,'montre connectée',9.74),
 (1,'smartphone',30.66),
 (2,'imprimante',75.68),
 (3,'console de salon',79.97),
 (4,'ordinateur portable',139.29),
 (5,'télévision',357.64),
 (6,'ordinateur fixe',425.36),
 (7,'aspirateur',47),
 (8,'cafetière',58.7),
 (9,'four','143,70'),
 (10,'réfrigérateur',263.56),
 (11,'lave-vaisselle',265.84),
 (12,'lave-linge',317.38);
INSERT INTO "BDD_Consommation_Moyens_de_transport" ("Id_Moyen_de_transport","Nom_Moyen_de_transport","kgCo2km") VALUES (0,'vélo ou marche',0),
 (1,'métro',0.003),
 (2,'vélo (ou trotinette) à assitance électrique',0.01),
 (3,'scooter ou moto légère ',0.08),
 (4,'voiture (moteur électrique)',0.1),
 (5,'bus',0.1),
 (6,'voiture (moteur thermique)',0.2);
INSERT INTO "Utilisateurs" ("Id_user","Admin_user","Username","user_Password","mail","firstName","lastName","country","birth","mobile") VALUES (1,1,'hamza','6b8bdda30e0b9992df8ce76576a8f6d6b9a1eb2c098184d59a345e96899a3367','hamza.lyautey@gmail.com','hamza','el arji','maroc','15/06/2002','0626116065'),
 (2,1,'mouloud','ea474f7dcafda10146f1b82b1900cd4c544d3fb97a8c55e129a27faa1f2889f9','mouloud@gmail.com','mouloud','tighlit','france','15/06/2002','0626116065'),
 (3,1,'elie','044e84410d94b9bdd856cfe01da6a7834b95a860792c84513c64dcf12066cfb5','elie@gmail.com','elie','bosle','france','15/06/2002','0626116065'),
 (4,1,'ulysse','054f16c7365e91509d242374660fe4fc12016ced1e82fe9078fb43cc8ba41020','ulysse@gmail.com','ulysse','ristorcelli','france','15/06/2002','0626116065'),
 (5,1,'jl','78965d853ab10797185a084a133da63bce7d5febe7d79710aef1597a0233312b','jl@gmail.com','jl','arette','france','15/06/2002','0626116065'),
 (6,0,'rayane','66033b5759b9be7110dff139ea89b1235300e5363d5ac6adf6c696407c28747e','rayane@gmail.com','rayane','el arji','maroc','15/06/2002','0626116065'),
 (7,0,'vincent','41eea29f92b4d39b39d9d5716911b8b1e575df3ea44ef800b0792e43a842c40c','vincent@gmail.com','vincent','feur','france','15/06/2002','0626116065'),
 (8,0,'idriss','403508b11fc810087460491aa2321591eb044653c3533006cb6e7dd7053e82a9','idriss@gmail.com','idriss','khayatei','maroc','15/06/2002','0626116065'),
 (9,0,'lilou','2def3fb494a0ea4649713186dfc3fddc95d8c328ddbb0c55ded235b7dc9d0a8e','lilou@gmail.com','lilou','genin','france','23/03/2003','0626116065');
COMMIT;
