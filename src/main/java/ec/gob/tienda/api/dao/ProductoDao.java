package ec.gob.tienda.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.gob.tienda.api.model.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long> {
/*
	List<Producto> findByCatalogoIdIn(List<Integer> ids);

	@Query("Select c from Producto c where c.catalogoId not in (:ids)")
	List<Producto> findByCatalogoIdsNot(@Param("ids") List<Integer> ids);*/
}
