CREATE TABLE `tiendas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tienda` (`name`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `identificacion` varchar(13) NOT NULL,
  `foto` longblob NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cliente` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `productos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cod` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_producto` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `inventarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tienda` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  `stock` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_inv_tienda` (`id_tienda`),
  KEY `fk_inv_prod` (`id_producto`),
  CONSTRAINT `fk_inv_tienda` FOREIGN KEY (`id_tienda`) REFERENCES `tienda` (`id`),
  CONSTRAINT `fk_inv_prod` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ventas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_cliente` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
    total decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_veta_cliente` (`id_cliente`),
  CONSTRAINT `fk_veta_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `tienda` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `det_ventas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_vta` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  `cantidad` bigint(20) NOT NULL,
  subtotal decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fh_det_vta` (`id_vta`),
  KEY `fk_det_prod` (`id_producto`),
  CONSTRAINT `fh_det_vta` FOREIGN KEY (`id_vta`) REFERENCES `tienda` (`id`),
  CONSTRAINT `fk_det_prod` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transacciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente` varchar(13) NOT NULL,
  `producto` varchar(20) NOT NULL,
  `cantidad` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `tienda` varchar(40) NOT NULL,
  subtotal decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),			
) ENGINE=InnoDB DEFAULT CHARSET=utf8;