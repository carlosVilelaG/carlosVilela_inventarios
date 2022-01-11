package ec.gob.tienda;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ec.gob.tienda.api.controller.ControllerConsulta;
import ec.gob.tienda.api.dto.InformacionTransaccion;
import ec.gob.tienda.api.model.Cliente;
import ec.gob.tienda.api.model.DetVenta;
import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.model.Tienda;
import ec.gob.tienda.api.model.Transaccion;
import ec.gob.tienda.api.model.Venta;
import ec.gob.tienda.api.service.IServicioConsultas;
import ec.gob.tienda.api.service.ServiciosTransaccionales;

@SpringBootTest
class TiendaApiApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired
	private IServicioConsultas servCons;
	
	@Mock
	public List<InformacionTransaccion> listaInformacionTransaccion;

	@Mock
	public List<Transaccion> listaTransaccionMock;
	
	@Autowired
	private ServiciosTransaccionales serviciosTransaccionales;
	
	
	private Cliente cargaCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setIdentificacion("0919999999");
		cliente.setNombre("Prueba");
		return cliente;
	}
	
	@Test
	public void pruebaExistenciaCliente() {
		Cliente cliente = cargaCliente();
		boolean resultado = servCons.existeCliente(cliente.getIdentificacion());
		assertTrue(resultado);
	}

	private List<Transaccion> cargaTransacciones() {
		List<Transaccion> listaTransaccion = new ArrayList<>();
		Transaccion transaccion = new Transaccion();
		transaccion.setId(1L);
		transaccion.setCliente("0919999999");
		transaccion.setProducto("prod-2");
		transaccion.setTienda("quicentro norte");
		transaccion.setCantidad(2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
        	fecha = formatter.parse("2021-01-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaccion.setFecha(fecha);
		listaTransaccion.add(transaccion);
		
		transaccion = new Transaccion();
		transaccion.setId(1L);
		transaccion.setCliente("0919999999");
		transaccion.setProducto("prod-2");
		transaccion.setTienda("quicentro norte");
		transaccion.setCantidad(1);
		try {
        	fecha = formatter.parse("2021-01-09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaccion.setFecha(fecha);
		InformacionTransaccion informacionTransaccion = new InformacionTransaccion(2L,"0919999999","prod-2");
		listaInformacionTransaccion = new ArrayList<>();
		listaInformacionTransaccion.add(informacionTransaccion);
		return listaTransaccion;
	}
	
	private List<Transaccion> cargaTransacciones2() {
		List<Transaccion> listaTransaccion = new ArrayList<>();
		Transaccion transaccion = new Transaccion();
		transaccion.setId(1L);
		transaccion.setCliente("0919999999");
		transaccion.setProducto("prod-2");
		transaccion.setTienda("quicentro norte");
		transaccion.setCantidad(2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
        	fecha = formatter.parse("2021-01-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaccion.setFecha(fecha);
		listaTransaccion.add(transaccion);
		
		transaccion = new Transaccion();
		transaccion.setId(1L);
		transaccion.setCliente("0919999999");
		transaccion.setProducto("prod-2");
		transaccion.setTienda("quicentro norte");
		transaccion.setCantidad(1);
		try {
        	fecha = formatter.parse("2021-01-09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaccion.setFecha(fecha);
		InformacionTransaccion informacionTransaccion = new InformacionTransaccion(2L,"0919999999","prod-2");
		listaInformacionTransaccion = new ArrayList<>();
		listaInformacionTransaccion.add(informacionTransaccion);
		return listaTransaccion;
	}
	@Test
	public void pruebaConsultaTransaccionAgrupadaTiendaProducto() {
		List<Transaccion> listaTransaccion = cargaTransacciones();
		List<InformacionTransaccion> listaInformacionTransaccionConsultada = servCons.transaccionesAgrupadasTiendaProducto();
	  assertTrue( listaInformacionTransaccionConsultada.size()> 0);
	}
	
	@Test
	public void pruebaConsultaTransaccionAgrupadaTiendaFecha() {
		List<Transaccion> listaTransaccion = cargaTransacciones();
		List<InformacionTransaccion> listaInformacionTransaccionConsultada = servCons.transaccionesAgrupadasTiendaFecha();
	  assertTrue( listaInformacionTransaccionConsultada.size()> 0);
	}
	@Test
	public void pruebaConsultaTransaccionClientePorRangoFechas() {
		listaTransaccionMock = cargaTransacciones();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicial = null;
        try {
        	fechaInicial = formatter.parse("2021-01-09");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Date fechaFinal = null;
        try {
        	fechaFinal = formatter.parse("2021-01-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        listaTransaccionMock = servCons.obtenerTransaccionesClienteRangoFechas("0919999999", fechaInicial, fechaFinal);	
	}

	@Test
   public void pruebaGuardaTransaccion() {
	   DetVenta detVenta = new DetVenta();
	   detVenta.setId(1L);
	   detVenta.setCantidad(2);
	   detVenta.setProducto(new Producto());
	   detVenta.getProducto().setId(1L);
	   detVenta.getProducto().setCod("prod-2");
	   detVenta.setTienda(new Tienda());
	   detVenta.getTienda().setId(1L);
	   detVenta.getTienda().setName("mool del sol");
	   detVenta.setSubtotal(new BigDecimal("20.0"));
	   Venta venta = new Venta();
	   venta.setId(1l);
	   venta.setCliente(new Cliente());
	   venta.getCliente().setId(1l);
	   venta.getCliente().setIdentificacion("0919999999");
	   	   
	   boolean resultado = serviciosTransaccionales.registraTransaccion(detVenta, venta);
	   assertTrue(resultado);
   }
 
}
