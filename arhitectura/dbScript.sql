CREATE TABLE `User` (
	`id_user` INT(20) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(30) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`email` VARCHAR(100) NOT NULL,
	`type` BOOLEAN NOT NULL,
	`telefon` INT(10) NOT NULL,
	PRIMARY KEY (`id_user`)
);

CREATE TABLE `Programari` (
	`id_programare` INT NOT NULL AUTO_INCREMENT,
	`id_user` INT NOT NULL,
	`model` VARCHAR(200) NOT NULL,
	`descriere` VARCHAR(200) NOT NULL,
	`data` DATE NOT NULL,
	`ora` TIME NOT NULL,
	`text_raspuns` VARCHAR(200) NOT NULL,
	`atachament_name` VARCHAR(200) NOT NULL,
	`pret` INT(200) NOT NULL,
	PRIMARY KEY (`id_programare`)
);

CREATE TABLE `Stocuri` (
	`id_stoc` INT(20) NOT NULL AUTO_INCREMENT,
	`id_piesa` INT(20) NOT NULL,
	`cantitate` INT(20) NOT NULL,
	PRIMARY KEY (`id_stoc`)
);

CREATE TABLE `Comenzi` (
	`id_comanda` INT(20) NOT NULL AUTO_INCREMENT,
	`id_piesa` INT(20) NOT NULL,
	`id_furnizor` INT(20) NOT NULL,
	`data_livrare` DATE NOT NULL,
	`bucati` INT(10) NOT NULL,
	PRIMARY KEY (`id_comanda`)
);

CREATE TABLE `Furnizori` (
	`id_furnizor` INT(20) NOT NULL AUTO_INCREMENT,
	`nume` VARCHAR(200) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (`id_furnizor`)
);

CREATE TABLE `Piesa` (
	`id_piesa` INT(20) NOT NULL AUTO_INCREMENT,
	`nume` VARCHAR(20) NOT NULL,
	`model` VARCHAR(20) NOT NULL,
	`an` INT(20) NOT NULL,
	`id_furnizor` INT(20) NOT NULL,
	`pret` INT(20) NOT NULL,
	PRIMARY KEY (`id_piesa`)
);

CREATE TABLE `Mesaj` (
	`id_mesaj` INT(20) NOT NULL AUTO_INCREMENT,
	`nume` VARCHAR(20) NOT NULL,
	`email` VARCHAR(20) NOT NULL,
	`text` VARCHAR(2000) NOT NULL,
	`citit` BOOLEAN NOT NULL,
	`telefon` INT(10) NOT NULL,
	PRIMARY KEY (`id_mesaj`)
);

ALTER TABLE `Programari` ADD CONSTRAINT `Programari_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id_user`);

ALTER TABLE `Stocuri` ADD CONSTRAINT `Stocuri_fk0` FOREIGN KEY (`id_piesa`) REFERENCES `Piesa`(`id_piesa`);

ALTER TABLE `Comenzi` ADD CONSTRAINT `Comenzi_fk0` FOREIGN KEY (`id_piesa`) REFERENCES `Piesa`(`id_piesa`);

ALTER TABLE `Comenzi` ADD CONSTRAINT `Comenzi_fk1` FOREIGN KEY (`id_furnizor`) REFERENCES `Furnizori`(`id_furnizor`);

ALTER TABLE `Piesa` ADD CONSTRAINT `Piesa_fk0` FOREIGN KEY (`id_furnizor`) REFERENCES `Furnizori`(`id_furnizor`);

