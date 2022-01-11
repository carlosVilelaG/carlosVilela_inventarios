package ec.gob.tienda.api.service;

import java.util.List;

import ec.gob.tienda.api.model.DetVenta;
import ec.gob.tienda.api.model.Inventario;
import ec.gob.tienda.api.model.Venta;

public interface IServiciosTransaccionales {


	public boolean registrarInventario(Inventario objDato);
	
	public boolean registrarVenta(Venta objDato, List<DetVenta> detVenta);
	

}