CREATE TABLE `User` (
	`id_user` INT(20)  ,
	`username` VARCHAR(30) ,
	`password` VARCHAR(100) ,
	`name` VARCHAR(100) ,
	`email` VARCHAR(100) ,
	`type` BOOLEAN ,
	`telefon` INT(10) ,
	PRIMARY KEY (`id_user`)
);

CREATE TABLE `Programari` (
	`id_programare` INT  ,
	`id_user` INT ,
	`model` VARCHAR(200) ,
	`descriere` VARCHAR(200) ,
	`data` DATE ,
	`ora` TIME ,
	`text_raspuns` VARCHAR(200) ,
	`atachament_name` VARCHAR(200) ,
	`pret` INT(200) ,
	PRIMARY KEY (`id_programare`)
);

CREATE TABLE `Stocuri` (
	`id_stoc` INT(20)  ,
	`id_piesa` INT(20) ,
	`cantitate` INT(20) ,
	PRIMARY KEY (`id_stoc`)
);

CREATE TABLE `Comenzi` (
	`id_comanda` INT(20)  ,
	`id_piesa` INT(20) ,
	`id_furnizor` INT(20) ,
	`data_livrare` DATE ,
	`bucati` INT(10) ,
	PRIMARY KEY (`id_comanda`)
);

CREATE TABLE `Furnizori` (
	`id_furnizor` INT(20)  ,
	`nume` VARCHAR(200)  ,
	PRIMARY KEY (`id_furnizor`)
);

CREATE TABLE `Piesa` (
	`id_piesa` INT(20)  ,
	`nume` VARCHAR(20) ,
	`model` VARCHAR(20) ,
	`an` INT(20) ,
	`id_furnizor` INT(20) ,
	`pret` INT(20) ,
	PRIMARY KEY (`id_piesa`)
);

CREATE TABLE `Mesaj` (
	`id_mesaj` INT(20)  ,
	`nume` VARCHAR(20) ,
	`email` VARCHAR(20) ,
	`text` VARCHAR(2000) ,
	`citit` BOOLEAN ,
	`telefon` INT(10) ,
	PRIMARY KEY (`id_mesaj`)
);

ALTER TABLE `Programari` ADD CONSTRAINT `Programari_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id_user`);

ALTER TABLE `Stocuri` ADD CONSTRAINT `Stocuri_fk0` FOREIGN KEY (`id_piesa`) REFERENCES `Piesa`(`id_piesa`);

ALTER TABLE `Comenzi` ADD CONSTRAINT `Comenzi_fk0` FOREIGN KEY (`id_piesa`) REFERENCES `Piesa`(`id_piesa`);

ALTER TABLE `Comenzi` ADD CONSTRAINT `Comenzi_fk1` FOREIGN KEY (`id_furnizor`) REFERENCES `Furnizori`(`id_furnizor`);

ALTER TABLE `Piesa` ADD CONSTRAINT `Piesa_fk0` FOREIGN KEY (`id_furnizor`) REFERENCES `Furnizori`(`id_furnizor`);

