package ec.gob.tienda.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.gob.tienda.api.dao.ClientesDao;
import ec.gob.tienda.api.dao.DetalleVentaDao;
import ec.gob.tienda.api.dao.InventarioDao;
import ec.gob.tienda.api.dao.TransaccionDao;
import ec.gob.tienda.api.dao.VentaDao;
import ec.gob.tienda.api.model.DetVenta;
import ec.gob.tienda.api.model.Inventario;
import ec.gob.tienda.api.model.Transaccion;
import ec.gob.tienda.api.model.Venta;

@Service
public class ServiciosTransaccionales implements IServiciosTransaccionales {

	@Autowired
	private InventarioDao serInventario;
	@Autowired
	private VentaDao ventaDao;
	@Autowired
	private DetalleVentaDao detalleVentaDao;
	@Autowired
	private ClientesDao clientesDao;
	@Autowired
	private ServicioProductos servicioProducto;
	@Autowired
	private TransaccionDao serTransaccion;
	
	@Override
	@Transactional
	public boolean registrarInventario(Inventario objDato) {
		boolean result = false;
		try {
			serInventario.saveAndFlush(objDato);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public boolean registrarVenta(Venta objDato, List<DetVenta> detVenta) {
		boolean result = false;
		try {
			
			if (objDato.getCliente().getId() == null && objDato.getCliente().getIdentificacion() != null) {
				clientesDao.saveAndFlush(objDato.getCliente());
			}
			
			ventaDao.saveAndFlush(objDato);					

			for(DetVenta detalle: detVenta) {
				servicioProducto.afectaStockProducto(detalle.getProducto().getId(), detalle.getProducto().getStock());
				registraTransaccion(detalle, objDato);
			}
			
			detalleVentaDao.saveAllAndFlush(detVenta);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ServicioProductos getServicioProducto() {
		return servicioProducto;
	}	
		
	
	public boolean registraTransaccion(DetVenta det, Venta objDato) {
		Transaccion nuevaTransaccion = new Transaccion();
		try {
			nuevaTransaccion.setCantidad(det.getCantidad());
			nuevaTransaccion.setProducto(det.getProducto().getCod());
			nuevaTransaccion.setCliente(objDato.getCliente().getIdentificacion());
			nuevaTransaccion.setTienda(det.getTienda().getName());
			nuevaTransaccion.setSubtotal(det.getSubtotal());
			nuevaTransaccion.setFecha(new Date());
			serTransaccion.save(nuevaTransaccion);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}