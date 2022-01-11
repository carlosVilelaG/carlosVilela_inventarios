package ec.gob.tienda.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.gob.tienda.api.dao.ProductoDao;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.util.GeneralUtil;

@Service
public class ServicioProductos implements IServicioProductos{
	
	@Autowired
	ProductoDao productoDao;
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<Producto> cargaInicialproductos(List<Producto> listaProducto) {
		productoDao.saveAll(listaProducto);
		return listaProducto;
	}

   public Producto afectaStockProducto(Long id, Integer cantidadVenta) {
	   Producto productoStock = new Producto();
	   Optional<Producto> producto = productoDao.findById(id);
	   if (producto.isPresent()) {
		   productoStock = producto.get();
		   productoStock.setStock(productoStock.getStock()-cantidadVenta);
	       productoDao.save(productoStock);
	   }
	   
	return productoStock;
   }
   
   public String consultayVerificaStockProducto(Long productoCompra, Integer cantidadCompra) {
	   Producto productoStock = new Producto();
	   Integer restanteOFaltanteStock;
	   Optional<Producto> producto = productoDao.findById(productoCompra);
	   if (producto.isPresent()) {
		   productoStock = producto.get();
		   /// Si es mayor la compra que stock, hay faltante en stock
		   if (cantidadCompra > productoStock.getStock()) {
			   restanteOFaltanteStock = cantidadCompra - productoStock.getStock();
		       if (restanteOFaltanteStock<= 5) {
		    	   return GeneralUtil.STOCK_FALTANTE_MENOR_A_CINCO;
		       }else if (restanteOFaltanteStock<= 10) {
				 return GeneralUtil.STOCK_FALTANTE_ENTRE_CINCO_Y_DIEZ;
			     } else {
					return GeneralUtil.STOCK_FALTANTE_MAYOR_A_DIEZ;
				}		       
		   }
	   }else {
		   return GeneralUtil.STOCK_NO_EXISTE;
	   }
	   return GeneralUtil.STOCK_OK;
   }

   public Producto peticionStockProducto(String peticionProducto, Long id) {
	   Producto producto = new Producto();
	   String apiUrlUsar =null;
	   
	   if(peticionProducto.equals(GeneralUtil.PETICION_10_PRODUCTOS)) {
		   apiUrlUsar = "https://mocki.io/v1/755e553c-9276-4784-99e0-fa36156bad11";
	   }else {
		   apiUrlUsar = "https://mocki.io/v1/405c731f-314f-4f22-b340-42eea573fb83";
	   }
	   Integer existencia = productoDao.findById(id).get().getStock();
	   ResponseEntity<Producto> responseEntity= new RestTemplate().getForEntity(apiUrlUsar, Producto.class);
	   
	   producto = responseEntity.getBody();           
       
       ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter()
			        .writeValueAsString(producto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	   System.out.println("peticion nuevo poducto "+ json);
	   producto.setId(id);
	   producto.setStock(producto.getStock() + existencia);		
	   productoDao.save(producto);
	   
		return producto;
   }
}