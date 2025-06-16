DROP DATABASE IF EXISTS filelec1;
CREATE DATABASE filelec1;
USE filelec1;

-- Table Client
CREATE TABLE client (
    id_client INT(5) NOT NULL AUTO_INCREMENT, 
    nom_client VARCHAR(50) NOT NULL, 
    prenom_client VARCHAR(50) NOT NULL, 
    adresse_client VARCHAR(50) NOT NULL, 
    email_client VARCHAR(100) NOT NULL , 
    tel_client CHAR(12) NOT NULL , 
    mdp_client VARCHAR(200) NOT NULL,
    date_creation_client DATE NOT NULL,
    url_client VARCHAR(255) NOT NULL,  
    PRIMARY KEY (id_client)
);


-- Table Catégorie
CREATE TABLE categorie (
    id_cat INT(10) NOT NULL AUTO_INCREMENT,
    nom_cat VARCHAR(25) NOT NULL,
    PRIMARY KEY (id_cat)
);

-- Table Article
CREATE TABLE article (
    id_article INT(10) NOT NULL AUTO_INCREMENT,
    nom_article VARCHAR(25) NOT NULL,
    description_article VARCHAR(100) NOT NULL,
    prix_article FLOAT(10,2) NOT NULL,
    stock_article INT DEFAULT 0,
    id_cat INT(10) NOT NULL,
    PRIMARY KEY (id_article),
    FOREIGN KEY (id_cat) REFERENCES categorie(id_cat)
);

-- Table Image Article (corrigée)
CREATE TABLE image (
    id_image INT(10) NOT NULL AUTO_INCREMENT,  
    nom_image VARCHAR(255) NOT NULL,                       
    url_image VARCHAR(255) NOT NULL,
    id_article INT(10) NOT NULL, 
    PRIMARY KEY (id_image),
    FOREIGN KEY (id_article) REFERENCES article(id_article)
);

-- Table Commande
CREATE TABLE commande (
    id_commande INT(10) NOT NULL AUTO_INCREMENT,
    id_client INT(5) NOT NULL,
    date_commande DATE NOT NULL,
    statut ENUM('en preparation', 'en chemin', 'livré') NOT NULL,
    montant_total FLOAT(10,2),
    adresse_livraison VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_commande),
    FOREIGN KEY (id_client) REFERENCES client(id_client)
);

-- Table Ligne de Commande
CREATE TABLE ligne (
    id_ligne INT(12) NOT NULL AUTO_INCREMENT,
    id_article INT(10) NOT NULL,
    quantite INT(5) NOT NULL,
    sous_total FLOAT(10,2),
    prix_unitaire FLOAT(10,2),
    id_commande INT(10) NOT NULL,
    PRIMARY KEY (id_ligne_cmd),
    FOREIGN KEY (id_commande) REFERENCES commande(id_commande),
    FOREIGN KEY (id_article) REFERENCES article(id_article)
);

-- Table Technicien
CREATE TABLE technicien (
    id_technicien INT(12) NOT NULL AUTO_INCREMENT,
    nom_technicien VARCHAR(25) NOT NULL,
    prenom_technicien VARCHAR(25) NOT NULL,
    email_technicien VARCHAR(200) NOT NULL UNIQUE,
    mdp_technicien VARCHAR(200) NOT NULL,
    telephone_technicien VARCHAR(20),
    role_technicien enum ("technicien", "admin", "user"),
    PRIMARY KEY (id_technicien)
);

-- Table Intervention
CREATE TABLE intervention (
    id_intervention INT(12) NOT NULL AUTO_INCREMENT,
    rapport text,
    date_debut_inter DATE NOT NULL,
    date_fin_inter DATE NOT NULL,
    id_client INT(5) NOT NULL,
    id_technicien INT(12) NOT NULL,
    id_commande INT(10) NOT NULL,
 	statut_inter enum ("Terminer", "En cours", "Annuler"),
    PRIMARY KEY (id_intervention),
    FOREIGN KEY (id_client) REFERENCES client(id_client),
    FOREIGN KEY (id_technicien) REFERENCES technicien(id_technicien),
    FOREIGN KEY (id_commande) REFERENCES commande(id_commande)
);


create table grainsel (
	nb varchar (100) not null primary key 
	
);

insert into grainsel values ("1234567890");


#insertion table technicien 
insert into technicien values (null, "Theo", "Valentin", "a@gmail.com", "123", "123456789", "admin");

# set @gs = "";
# select nb into @gs from grainsel ; 

# update technicien set mdp = sha1(concat(mdp,@gs)); 


#insertion table client 






# procédures stockees 
delimiter $
 create procedure deleteClient(IN p_idclient INT)
 BEGIN
 delete from intervention where id_client = p_idclient;
 delete from commande where id_client = p_idclient;
 delete from client where id_client = p_idclient;
 END $
 delimiter ;



