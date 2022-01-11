package ec.gob.tienda.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.gob.tienda.api.model.Tienda;

public interface TiendaDao extends JpaRepository<Tienda, Long> {

}
