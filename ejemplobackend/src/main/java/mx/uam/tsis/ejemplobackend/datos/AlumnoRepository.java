package mx.uam.tsis.ejemplobackend.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;



@Component
public class AlumnoRepository {
	// La "base de datos"
		private Map <Integer, Alumno> alumnoRepository = new HashMap <>();
		
		//uarda en la base de datos
		public Alumno save(Alumno nuevoAlumno) {
			alumnoRepository.put(nuevoAlumno.getMatricula(), nuevoAlumno);
			return nuevoAlumno;
		}
		
		public Alumno findbyMatricula(Integer matricula) {
			return alumnoRepository.get(matricula);
		}
		
		public List<Alumno>find(){
			return new ArrayList<>(alumnoRepository.values());
		}
		
		public Alumno modify(Integer matricula,Alumno alumnoActual) {
			Alumno alumno = alumnoRepository.get(matricula);
			alumno.setNombre(alumnoActual.getNombre());
			alumno.setCarrera(alumnoActual.getCarrera());
			alumnoRepository.put(matricula, alumno);
			return alumno;
			
		}
		
		public Alumno delete(Integer matricula) {
			return alumnoRepository.remove(matricula);
			
		}
		

}

