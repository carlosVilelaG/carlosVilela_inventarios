BEGIN;
--TRUNCATE tiendas;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE tiendas;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `tienda`.`tiendas`
(`id`,`name`)
VALUES('001','moll del sol'),
('002','quicentro norte'),
('003','riocentro sur'),
('004','plataforma gubernamental');
commit;