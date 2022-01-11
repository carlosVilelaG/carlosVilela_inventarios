package ec.gob.tienda.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.gob.tienda.api.model.DetVenta;

public interface DetalleVentaDao extends JpaRepository<DetVenta, Long> {

}
