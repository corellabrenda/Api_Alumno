package mx.uam.tsis.ejemplobackend.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class AlumnoControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@BeforeEach
	public void prepare() {
		
		// Aqui se puede hacer cosas para preparar sus casos de prueba, incluyendo
		// agregar datos a la BD
		
		
	}
	
	
	
	public void testCreate201() {
		
		// Creo el alumno que voy a enviar
		Alumno alumno = new Alumno();
		alumno.setCarrera("ComputaciÃ³n");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");

		// Creo el encabezado
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type",MediaType.APPLICATION_JSON.toString());
		
		// Creo la peticiÃ³n con el alumno como body y el encabezado
		HttpEntity <Alumno> request = new HttpEntity <> (alumno, headers);
		
		ResponseEntity <Alumno> responseEntity = restTemplate.exchange("/alumnos", HttpMethod.POST, request, Alumno.class);

		log.info("Me regresÃ³:"+responseEntity.getBody());
		
		// Corroboro que el endpoint me regresa el estatus esperado
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		
		// Corroboro que en la base de datos se guardÃ³ el alumno
		Optional <Alumno> optAlumno = alumnoRepository.findById(12345678);
		assertEquals(alumno,optAlumno.get());
		
	}

	@Test
	public void testCreate400() {
		
	}
	


}
