BEGIN;
--TRUNCATE productos;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE productos;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `tienda`.`productos`
(`cod`,`name`,`price`)
VALUES('001','galletas',1.25),
('002','arroz',1.55),
('003','aceite',2.25),
('004','sal',0.35),
('005','fideo',1.35);
commit;
