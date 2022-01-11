package ec.gob.tienda.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ec.gob.tienda.api.model.Producto;
import ec.gob.tienda.api.service.ServicioProductos;

@Component
public class MyApplicationRunner implements ApplicationRunner{
	
	private final Logger log = LoggerFactory.getLogger(MyApplicationRunner.class);
 
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ServicioProductos servicioProductos;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("incio de cara Inicial de Productos!");
		getProductosIniciales();
	}
	
	public List<Producto> getProductosIniciales(){
		String apiUri = "https://mocki.io/v1/8f7f24da-64e8-4bef-a282-200318b2606e";
				
		ParameterizedTypeReference<List<Producto>> referenciaObjeto =
				new ParameterizedTypeReference<List<Producto>>() {};
				
		ResponseEntity<List<Producto>> productosResponseiniciales = restTemplate
				.exchange(apiUri, HttpMethod.GET, null, referenciaObjeto);
  
        List<Producto> listadoProductos = productosResponseiniciales.getBody();
        
        productosResponseiniciales.getBody().forEach(c -> log.info("Productos: {}", c.toString()));
        
        ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter()
			        .writeValueAsString(listadoProductos);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		servicioProductos.cargaInicialproductos(listadoProductos);
		System.out.println("SALIDA JSON PRODUCTOS: \n" + json);
        
		return  listadoProductos;
    }

}