package ec.gob.tienda.api.service;

import java.util.List;

import ec.gob.tienda.api.model.Producto;

public interface IServicioProductos {

public List<Producto> cargaInicialproductos(List<Producto> listaProducto);

public Producto afectaStockProducto(Long id, Integer cantidadVenta);

public String consultayVerificaStockProducto(Long productoCompra, Integer cantidadCompra);

public Producto peticionStockProducto(String peticionProducto, Long id);
}
