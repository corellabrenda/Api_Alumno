package mx.uam.tsis.ejemplobackend.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocion.AlumnoService;

/**
 * Controlador para el API rest
 * 
 * @author humbertocervantes
 *
 */
@RestController
@Slf4j
public class AlumnoController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@ApiOperation(
			value="Crear alumno",
			notes="Permite crear un nuevo alumno, la matricula debe ser unica")
	@PostMapping(path = "/alumnos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Alumno nuevoAlumno) {
				
		log.info("Recibí llamada a create con "+nuevoAlumno);
		
		Alumno alumno = alumnoService.create(nuevoAlumno);
		
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(alumno);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	

	}
	
	
	@ApiOperation(
			value="Recupera todos alumnos",
			notes="Permite recuperar todos los alumnos exixtentes en la base de datos, devuelve todos los datos de los alumnos")
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Alumno> result = alumnoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}

	/*
	@ApiOperation(
			value="Recupera un alumno",
			notes="Permite recuperar un alumno con su matricula, la matricula dbe corresponder a un alumno previamente registrado")
	@GetMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@Valid @PathVariable ("matricula") Integer matricula) {
		log.info("Buscando al alumno con matricula "+matricula);
		Optional<Alumno> alumno=alumnoService.retrieve(matricula);
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno con la matricula"+matricula+"no exite");
		}
	
		
	}
	
*/
	/*@ApiOperation(
			value="Modifica alumno",
			notes="Permite modificar los datos de un alumno, Se necesita la matricula del alumno y un objeto con todos los datos del alumno")
	@PutMapping(path = "/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@Valid @PathVariable("matricula") Integer matricula,@RequestBody  @Valid Alumno nuevoAlumno ) {
		Alumno alumno = alumnoService.(matricula, nuevoAlumno);
		System.out.println("ALUMNO"+alumno);
		if(alumno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(alumno);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}*/
	
	/*
	@ApiOperation(
			value="Borra alumno",
			notes="Permite borrar un alumno de nuestra base de datos. Requiere la matricula del alumno.")
	@DeleteMapping(path="/alumnos/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>delete(@Valid @PathVariable("matricula") Integer matricula) {
		Alumno alumno = alumnoService.borra(matricula);
		if(alumno!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("ha sido eliminado: "+alumno);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	*/
 
}
