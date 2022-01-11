package ec.gob.tienda.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.gob.tienda.api.model.Venta;

public interface VentaDao extends JpaRepository<Venta, Long> {

}
