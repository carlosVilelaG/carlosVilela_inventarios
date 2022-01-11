package ec.gob.tienda.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.gob.tienda.api.dao.ClientesDao;
import ec.gob.tienda.api.dao.ProductoDao;
import ec.gob.tienda.api.dao.TransaccionDao;
import ec.gob.tienda.api.dto.InformacionTransaccion;
import ec.gob.tienda.api.model.Cliente;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.model.Transaccion;


@Service
public class ServicioConsultas implements IServicioConsultas {

	@Autowired
	private ProductoDao servProducto;

	@Autowired
	private TransaccionDao serTransacciones;

	@Autowired
	private TransaccionesReporte transaccionesReporte;
	
	@Autowired
	private ClientesDao clienteDao;
	
	@Override
	public List<Producto> obtenerAllProductos() {
		return servProducto.findAll();
	}

	public List<Transaccion> obtenerAllTransacciones() {
		return serTransacciones.findAll();
	}
	
	public List<InformacionTransaccion> transaccionesAgrupadasTiendaFecha() {
		return transaccionesReporte.findTransactionCountTiendaFecha();
	}

	public List<InformacionTransaccion> transaccionesAgrupadasTiendaProducto() {
		return transaccionesReporte.findTransactionCountTiendaProducto();
	}
	
	public List<Transaccion> obtenerTransaccionesClienteRangoFechas(String cliente, Date fechaIni, Date fechaFin) {
		return serTransacciones.findbyclientebyfechas(cliente, fechaIni, fechaFin);
	}
	
	public boolean existeCliente(String identificacionCliente) {
		Optional<Cliente> clienteOptional = clienteDao.findByIdentificacion(identificacionCliente);
		if (clienteOptional.isPresent()) {
			return true;
		}
		return false;
	}
}