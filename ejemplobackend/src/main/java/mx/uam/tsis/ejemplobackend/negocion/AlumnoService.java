package mx.uam.tsis.ejemplobackend.negocion;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;

@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	/**
	 * 
	 * @param nuevoAlumno
	 * @return el alumno que se acaba de crear si la creacion es exitosa, null de lo contrario
	 */
public Alumno create(Alumno nuevoAlumno) {
		
		// Regla de negocio: No se puede crear más de un alumno con la misma matricula
		Optional <Alumno> alumnoOpt = alumnoRepository.findById(nuevoAlumno.getMatricula());
		
		if(!alumnoOpt.isPresent()) {
			
			return alumnoRepository.save(nuevoAlumno);
			
		} else {
			
			return null;
			
		}
		
	}
	

	public Iterable <Alumno> retrieveAll () {
		return alumnoRepository.findAll();
	}


	public Alumno retrieve(Integer matricula) {
		
		  Optional<Alumno> alumno=alumnoRepository.findById(matricula);
		  return alumno.get();
	}


	public Alumno update(@Valid Integer matricula, @Valid Alumno nuevoAlumno) {
		if(alumnoRepository.existsById(matricula)) {
			return alumnoRepository.save(nuevoAlumno);
		}else
			return null;
	}
	public boolean delete(Integer matricula) {
		if (alumnoRepository.existsById(matricula)) {
			alumnoRepository.deleteById(matricula);
			return true;
		}else 
		{
			return false;
		}
	}
	
	public Alumno findByMatricula(Integer matricula) {

		// LÃ³gica de negocio
		
		Optional <Alumno> alumnoOpt = alumnoRepository.findById(matricula);
		
		return alumnoOpt.get();
	}

	
	
}