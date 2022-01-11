package ec.gob.tienda.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.gob.tienda.api.model.Inventario;

public interface InventarioDao extends JpaRepository<Inventario, Long> {

}
