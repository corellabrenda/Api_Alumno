package mx.uam.tsis.ejemplobackend.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrupoControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@BeforeEach
	public void prepare() {
		
		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD
		
		
	}
public void testCreate201() {
		
		// Creo el alumno que voy a enviar
		Alumno alumno = new Alumno();
		alumno.setCarrera("Computacion");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la peticiÃ³n con el alumno como body y el encabezado
		HttpEntity <Alumno> request = new HttpEntity <> (alumno, headers);
		HttpEntity <Grupo> request1 = new HttpEntity <> (grupo, headers);
		
		ResponseEntity<Grupo> responseEntity = restTemplate.exchange("/grupo", HttpMethod.POST, request1, Grupo.class);
		log.info("Me regresÃ³:"+responseEntity.getBody());

		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		
	}

}
