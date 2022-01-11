package ec.gob.tienda.api.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.gob.tienda.api.model.Transaccion;

public interface TransaccionDao extends JpaRepository<Transaccion, Long>{

	@Query("SELECT t FROM Transaccion t "
			+ " WHERE t.cliente = ?1 AND t.fecha BETWEEN ?2 AND ?3 ")
	public List<Transaccion> findbyclientebyfechas(String cliente, Date desde,Date hasta);

}
