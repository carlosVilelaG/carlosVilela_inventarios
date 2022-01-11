package ec.gob.tienda.api.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import ec.gob.tienda.api.dto.InformacionTransaccion;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.model.Transaccion;
import ec.gob.tienda.api.payload.ApiResponse;
import ec.gob.tienda.api.service.IServicioConsultas;
import ec.gob.tienda.api.util.AppMensajes;

@RestController
@RequestMapping("/tienda-api")
public class ControllerConsulta {

	@Autowired
	private IServicioConsultas servCons;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/cargaDatosIniciales", consumes = { MediaType.APPLICATION_JSON_VALUE, //
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> cargarDatosIniciales() {

		ApiResponse response = new ApiResponse();
		try {			
			response.setCodMensaje(AppMensajes.COD_EXITO.getCodigo());
			response.getMensajes().add(AppMensajes.COD_EXITO.getDescripcion());
		} catch (Exception e) {
			response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
			response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/obtenerAllProductos")
	public ResponseEntity<?> consultarAllProductos() {
		List<HashMap<String, String>> datos = new ArrayList<HashMap<String,String>>();
		ApiResponse response = new ApiResponse();
		try {
		    HashMap<String, String> map = new HashMap<>();
			for (Producto p : servCons.obtenerAllProductos()){
				map = new HashMap<>();
				map.put("cod", p.getCod());
				map.put("name", p.getName());
				datos.add(map);
			}
			response.setResult(datos);
			response.setCodMensaje(AppMensajes.COD_EXITO.getCodigo());
			response.getMensajes().add(AppMensajes.COD_EXITO.getDescripcion());
		} catch (Exception e) {
			response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
			response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);

	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/obtenerTransacciones")
	public ResponseEntity<?> consultarTransacciones() {
		List<HashMap<String, String>> datos = new ArrayList<HashMap<String,String>>();
		ApiResponse response = new ApiResponse();
		try {
		    HashMap<String, String> map = new HashMap<>();
			for (Transaccion p : servCons.obtenerAllTransacciones()){
				map = new HashMap<>();
				map.put("cod", p.getId().toString());
				map.put("name", p.getProducto().getClass().getName());
				datos.add(map);
			}
			response.setResult(datos);
			response.setCodMensaje(AppMensajes.COD_EXITO.getCodigo());
			response.getMensajes().add(AppMensajes.COD_EXITO.getDescripcion());
		} catch (Exception e) {
			response.setCodMensaje(AppMensajes.COD_ERROR_GEN.getCodigo());
			response.getMensajes().add(AppMensajes.COD_ERROR_GEN.getDescripcion());
			e.printStackTrace();
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}	
	
	 @GetMapping("/transacciones/reportTiendaFecha")
	    public void consultatransaccionReporte(HttpServletResponse response) throws IOException {
	        response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=reporteTiendaFecha_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<InformacionTransaccion> listTransacciones = servCons.transaccionesAgrupadasTiendaFecha();
	 
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"Numero Transaccion", "Tienda", "Fecha"};
	        String[] nameMapping = {"campo1", "campo2", "campo4"};
	         
	        csvWriter.writeHeader(csvHeader);
	         
	        for (InformacionTransaccion tr : listTransacciones) {
	            csvWriter.write(tr, nameMapping);
	        }
	         
	        csvWriter.close();
	         
	    }
	
	 @GetMapping("/transacciones/reportTiendaProducto")
	    public void consultatransaccionReporteTiendaProducto(HttpServletResponse response) throws IOException {
	        response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=reporteTiendaProducto_" + currentDateTime + ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<InformacionTransaccion> listTransacciones = servCons.transaccionesAgrupadasTiendaProducto();
	 
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"Numero Transaccion", "Tienda", "Producto"};
	        String[] nameMapping = {"campo1", "campo2", "campo3"};
	         
	        csvWriter.writeHeader(csvHeader);
	         
	        for (InformacionTransaccion tr : listTransacciones) {
	            csvWriter.write(tr, nameMapping);
	        }
	         
	        csvWriter.close();
	         
	    }
	 
	    @GetMapping("/transacciones/exportTransaccionesCliente")	    
	    public ResponseEntity<?> exportToCSVTransaccionesClienteFechas(@RequestParam(name="identificacion") String identificacionCliente,
	    		@RequestParam(name="fechaI") String fechaInicio, @RequestParam(name="fechaF") String fechaFin, 
	    		HttpServletResponse response) throws IOException {
//     /http://localhost:8280/tienda-api/transacciones/exportTransaccionesCliente?identificacion=0919999999&fechaI=2022-01-09&fechaF=2022-01-10
	        response.setContentType("text/csv");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = dateFormatter.format(new Date());
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	        Date fechaInicial = null;
	        try {
	        	fechaInicial = formatter.parse(fechaInicio);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Date fechaFinal = null;
	        try {
	        	fechaFinal = formatter.parse(fechaFin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        if (!servCons.existeCliente(identificacionCliente)) {
	        	return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_NO_EXISTE_CLIENTE.getCodigo(),
						AppMensajes.COD_ERROR_NO_EXISTE_CLIENTE.getDescripcion()), HttpStatus.OK);
	        }
	        
	        if (!(fechaFinal.after(fechaInicial) || fechaInicial.equals(fechaFinal))) {
	        	return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_FECHAS_NO_ADECUADAS.getCodigo(),
						AppMensajes.COD_ERROR_FECHAS_NO_ADECUADAS.getDescripcion()), HttpStatus.OK);
	        }
	        	        
		        String headerKey = "Content-Disposition";
		        String headerValue = "attachment; filename=transaccionesCliente_" + currentDateTime + ".csv";
		        response.setHeader(headerKey, headerValue);
		        
		        Calendar c = Calendar.getInstance(); 
				c.setTime(fechaFinal); 
				c.add(Calendar.DATE, 1);
				fechaFinal = c.getTime();
				
		        List<Transaccion> listTransacciones = servCons.obtenerTransaccionesClienteRangoFechas(identificacionCliente,
						fechaInicial, fechaFinal);

				
				 if (listTransacciones.isEmpty()) {
					 return new ResponseEntity(new ApiResponse(AppMensajes.COD_ERROR_CONSULTA_SIN_RESULTADO.getCodigo(),
								AppMensajes.COD_ERROR_CONSULTA_SIN_RESULTADO.getDescripcion()), HttpStatus.OK);
				 }
		 
		        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		        String[] csvHeader = {"Transaccion ID", "cantidad Producto", "codigo producto", "Fecha Transaccion", "Identificacion Cliente","Tienda","Total"};
		        String[] nameMapping = {"id", "cantidad", "producto", "fecha", "cliente","tienda","subtotal"};
		         
		        csvWriter.writeHeader(csvHeader);
		         
		        for (Transaccion tr : listTransacciones) {
		            csvWriter.write(tr, nameMapping);
		        }
		         
		        csvWriter.close();	
		       
			return null;
	    }	 	 
}