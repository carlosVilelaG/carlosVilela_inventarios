package ec.gob.tienda;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class TiendaApiApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	ObjectMapper myObjectMapper() {
		Hibernate5Module m = new Hibernate5Module();
		m.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(m);
		return mapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TiendaApiApplication.class, args);
	}

}
