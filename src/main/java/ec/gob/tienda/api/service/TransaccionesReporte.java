package ec.gob.tienda.api.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ec.gob.tienda.api.dto.InformacionTransaccion;
import ec.gob.tienda.api.model.Transaccion;

public interface TransaccionesReporte extends CrudRepository<Transaccion, Long> {

	@Query("SELECT " +
	           "    new ec.gob.tienda.api.dto.InformacionTransaccion(COUNT(t) , t.tienda, t.fecha ) " +
	           "FROM " +
	           "    Transaccion t " +
	           "GROUP BY " +
	           "    t.tienda, t.fecha")
	List<InformacionTransaccion> findTransactionCountTiendaFecha();
	
	@Query("SELECT " +
	           "    new ec.gob.tienda.api.dto.InformacionTransaccion(COUNT(t) , t.tienda, t.producto) " +
	           "FROM " +
	           "    Transaccion t  " +
	           "GROUP BY " +
	           "    t.tienda, t.producto")
	List<InformacionTransaccion> findTransactionCountTiendaProducto();

}
