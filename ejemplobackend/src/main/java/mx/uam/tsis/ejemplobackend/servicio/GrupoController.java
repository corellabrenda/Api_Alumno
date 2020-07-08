package mx.uam.tsis.ejemplobackend.servicio;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;
import mx.uam.tsis.ejemplobackend.negocion.GrupoService;

@RestController

@Slf4j 
public class GrupoController {
	
	
	@Autowired
	private GrupoService grupoService;
	
	
	@ApiOperation(
			value = "Crear grupo",
			notes = "Permite crear un nuevo grupo"
			) // Documentacion del api
	@PostMapping(path = "/grupos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Grupo nuevoGrupo) { // Validaciones
				
		log.info("RecibÃ­ llamada a create con "+nuevoGrupo); // Logging
		
		Grupo grupo = grupoService.create(nuevoGrupo);
		
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(grupo);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear alumno");
		}
	

	}
	
	@GetMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Grupo> result = grupoService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}
	@GetMapping(path = "/grupos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@Valid @PathVariable ("id") Integer id) {
		
		Grupo grupo=grupoService.retrieve(id);
		if(grupo != null) {
			return ResponseEntity.status(HttpStatus.OK).body(grupo);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El alumno con la matricula"+id+"no exite");
		}
	
		
	}
	
	@DeleteMapping(path="/grupos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?>delete(@Valid @PathVariable("id") Integer id) {
		Boolean grupo = grupoService.delete(id);
		if(grupo==true) {
			return ResponseEntity.status(HttpStatus.OK).body("ha sido eliminado: "+id);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
	}
	
	

	/**
	 * 
	 * POST /grupos/{id}/alumnos?matricula=1234
	 * 
	 * PROBAR ESTE!!!
	 * 
	 * @return
	 */
	@PostMapping(path = "/grupos/{id}/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("matricula") Integer matricula) {
		
		boolean result = grupoService.addStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}

}
