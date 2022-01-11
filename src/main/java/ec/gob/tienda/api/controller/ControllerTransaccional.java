package ec.gob.tienda.api.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.tienda.api.model.DetVenta;
import ec.gob.tienda.api.model.Inventario;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.model.Tienda;
import ec.gob.tienda.api.model.Venta;
import ec.gob.tienda.api.payload.ApiResponse;
import ec.gob.tienda.api.service.ServiciosTransaccionales;
import ec.gob.tienda.api.util.AppMensajes;
import ec.gob.tienda.api.util.GeneralUtil;

@RestController
@RequestMapping("/tienda-api/tr")
public class ControllerTransaccional {
	@Autowired
	private ServiciosTransaccionales servTrx;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/registrarActualizarInventario", consumes = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> registrarActualizarInventario( @RequestBody Producto producto,  BigInteger cantidad, Tienda tienda			) {
		ApiResponse response = new ApiResponse();
		Boolean result = false;
		try {
			if (cantidad.compareTo(BigInteger.ZERO) <= 0) {
				return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_STOCK_PRODUCTO_INVALIDO.getCodigo(),
						AppMensajes.COD_ERROR_STOCK_PRODUCTO_INVALIDO.getDescripcion()), HttpStatus.OK);
			}
			Inventario objDato = new Inventario();
			objDato.setProducto(producto);
			objDato.setTienda(tienda);
			objDato.setStock(cantidad);
			result = servTrx.registrarInventario(objDato);
			if(result){
				response.setCodMensaje(AppMensajes.COD_EXITO.getCodigo());
				response.getMensajes().add(AppMensajes.COD_EXITO.getDescripcion());
			}
			else
			{
				response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
				response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			}
			
		} catch (Exception e) {
			response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
			response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/registrarVentaProducto", consumes = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> registrarVenta( @RequestBody List<DetVenta> detalleVenta, @RequestBody Venta venta	) {
		ApiResponse response = new ApiResponse();
		Boolean result = false;
		String resultadoStock=null;
		try {
         
			for(DetVenta detalle: detalleVenta) {
				resultadoStock = servTrx.getServicioProducto().
				consultayVerificaStockProducto(detalle.getProducto().getId(), detalle.getCantidad());
				if (resultadoStock.equals(GeneralUtil.STOCK_FALTANTE_ENTRE_CINCO_Y_DIEZ)) {
					 servTrx.getServicioProducto().peticionStockProducto(GeneralUtil.PETICION_10_PRODUCTOS,detalle.getProducto().getId());
				}else if (resultadoStock.equals(GeneralUtil.STOCK_FALTANTE_MENOR_A_CINCO)) {
					servTrx.getServicioProducto().peticionStockProducto(GeneralUtil.PETICION_5_PRODUCTOS,detalle.getProducto().getId());
				}else if (resultadoStock.equals(GeneralUtil.STOCK_FALTANTE_MAYOR_A_DIEZ)) {
					return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_STOCK_UNIDAD_NO_DISPONIBLE.getCodigo(),
							AppMensajes.COD_ERROR_STOCK_UNIDAD_NO_DISPONIBLE.getDescripcion()), HttpStatus.OK);
				}else if (resultadoStock.equals(GeneralUtil.STOCK_NO_EXISTE)) {
					return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_STOCK_PRODUCTO_INVALIDO.getCodigo(),
							AppMensajes.COD_ERROR_STOCK_PRODUCTO_INVALIDO.getDescripcion()), HttpStatus.OK);
				}
				resultadoStock = null;
			}
			
			result = servTrx.registrarVenta(venta,detalleVenta);
			
			if(result){
				response.setCodMensaje(AppMensajes.COD_EXITO.getCodigo());
				response.getMensajes().add(AppMensajes.COD_EXITO.getDescripcion());
			}
			else
			{
				response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
				response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			}
			
		} catch (Exception e) {
			response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
			response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}	
}