package ec.gob.tienda.api.dao;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ec.gob.tienda.api.model.Cliente;

public interface ClientesDao extends JpaRepository<Cliente, Long> {
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByIdentificacion(String identificacion);
}
