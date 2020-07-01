package mx.uam.tsis.ejemplobackend.datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;




public interface AlumnoRepository extends CrudRepository<Alumno,Integer> {//Entidad y tipo de la llave primaria
	
}

