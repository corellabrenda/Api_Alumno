package mx.uam.tsis.ejemplobackend.negocion;

import java.util.List;

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
		Alumno alumno = alumnoRepository.findbyMatricula(nuevoAlumno.getMatricula());
		
		if(alumno == null) {
			
			return alumnoRepository.save(nuevoAlumno);
			
		} else {
			
			return null;
			
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List <Alumno> retrieveAll () {
		return alumnoRepository.find();
	}
	
	public Alumno retrieve(Integer Matricula) {
		return alumnoRepository.findbyMatricula(Matricula);
	}
	
	public Alumno modifica(Integer matricula, Alumno alumno) {
		return alumnoRepository.modify(matricula, alumno);
	}
	public Alumno borra(Integer matricula) {
		return alumnoRepository.delete(matricula);
	}
}