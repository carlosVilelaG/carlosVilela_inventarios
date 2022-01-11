package ec.gob.tienda;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.gob.tienda.api.dao.ProductoDao;
import ec.gob.tienda.api.dto.Stock;
import ec.gob.tienda.api.model.Producto;

@SpringBootTest
@TestPropertySource(locations = "classpath:datasource.properties")
@Sql("/db/insertProductos.sql")
public class CargaProductosApplicationTests {

	@Autowired
	private ProductoDao productoDao;

	@Test
	public void testLoadDataForTestClass() {
		System.out.println("#############################");
		System.out.println("#### CARGAR DATA ####");
		System.out.println("#############################");
		assertTrue(productoDao.findAll().size()>0);
	}

	@Test
	public void mostrarInventario() {
		System.out.println("#############################");
		System.out.println("#### MOSTRAR INVENTARIO ####");
		System.out.println("#############################");

		List<Producto> productos = productoDao.findAll();
		List<Stock> prods = new ArrayList<Stock>();
		for (Producto p : productos) {
			Stock s = new Stock();
			Long id =   (long) (Math.random()*6 + 1);
			s.setId(id );
			s.setCod(p.getCod());
			s.setName(p.getName());
			s.setPrice(p.getPrice());
			s.setStock(10);
			prods.add(s);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter()
			        .writeValueAsString(prods);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("SALIDA JSON: \n" + json);
		
		assertNotNull(json);
	}
}
