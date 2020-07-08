package mx.uam.tsis.ejemplobackend.negocion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;

@Service
@Slf4j
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private AlumnoService alumnoService;
	
	public Grupo create(Grupo nuevo) {
		
		// Validar reglas de negocio previas a la creaciÃ³n
		
		return grupoRepository.save(nuevo);
	}
	
	public Iterable <Grupo> retrieveAll() {
		return grupoRepository.findAll();
	}
	public Grupo retrieve(Integer id) {
		
		  Optional<Grupo> grupo=grupoRepository.findById(id);
		  return grupo.get();
	}
	
	public boolean delete(Integer id) {
		if (grupoRepository.existsById(id)) {
			grupoRepository.deleteById(id);
			return true;
		}else 
		{
			return false;
		}
	}
	
	/**
	 * 
	 * MÃ©todo que permite agregar un alumno a un grupo
	 * 
	 * @param groupId el id del grupo
	 * @param matricula la matricula del alumno
	 * @return true si se agregÃ³ correctamente, false si no
	 */
	public boolean addStudentToGroup(Integer groupId, Integer matricula) {
		
		log.info("Agregando alumno con matricula "+matricula+" al grupo "+groupId);
		
		// 1.- Recuperamos primero al alumno
		Alumno alumno = alumnoService.findByMatricula(matricula);
		
		// 2.- Recuperamos el grupo
		Optional <Grupo> grupoOpt = grupoRepository.findById(groupId);
		
		// 3.- Verificamos que el alumno y el grupo existan
		if(!grupoOpt.isPresent() || alumno == null) {
			
			log.info("No se encontrÃ³ alumno o grupo");
			
			return false;
		}
		
		// 4.- Agrego el alumno al grupo
		Grupo grupo = grupoOpt.get();
		grupo.addAlumno(alumno);
		
		// 5.- Persistir el cambio
		grupoRepository.save(grupo);
		
		return true;
	}
	
	

}
