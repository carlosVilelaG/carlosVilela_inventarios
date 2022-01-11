package ec.gob.tienda.api.service;

import java.util.Date;
import java.util.List;

import ec.gob.tienda.api.dto.InformacionTransaccion;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.model.Transaccion;

public interface IServicioConsultas {


	public List<Producto> obtenerAllProductos();
	
	public List<Transaccion> obtenerAllTransacciones();
	
	public List<InformacionTransaccion> transaccionesAgrupadasTiendaFecha() ;

	public List<InformacionTransaccion> transaccionesAgrupadasTiendaProducto() ;

	public List<Transaccion> obtenerTransaccionesClienteRangoFechas(String cliente, Date fechaIni, Date fechaFin);
	
	public boolean existeCliente(String identificacionCliente);
}
