package mx.uam.tsis.ejemplobackend.negocio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import mx.uam.tsis.ejemplobackend.datos.AlumnoRepository;
import mx.uam.tsis.ejemplobackend.datos.GrupoRepository;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Alumno;
import mx.uam.tsis.ejemplobackend.negocio.modelo.Grupo;
import mx.uam.tsis.ejemplobackend.negocion.AlumnoService;
import mx.uam.tsis.ejemplobackend.negocion.GrupoService;

import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Uso de Mockito
public class GrupoServiceTest {

	@Mock
	private GrupoRepository grupoRepositoryMock;
	
	@Mock
	private AlumnoService alumnoServiceMock;
	
	@InjectMocks
	private GrupoService grupoService;
	
	@Test
	public void testSuccesfulAddStudentToGroup (){
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");

		Alumno alumno = new Alumno();
		alumno.setCarrera("ComputaciÃ³n");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.findByMatricula(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(true,result);
		
		assertEquals(grupo.getAlumnos().get(0),alumno);
		
	}
	
	@Test
	public void testUnsuccesfulAddStudentToGroup (){
		
		Alumno alumno = new Alumno();
		alumno.setCarrera("ComputaciÃ³n");
		alumno.setMatricula(12345678);
		alumno.setNombre("Pruebin");
		
		// Stubbing para el alumnoService
		when(alumnoServiceMock.findByMatricula(12345678)).thenReturn(alumno);
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(anyInt())).thenReturn(Optional.ofNullable(null));
		
		
		boolean result = grupoService.addStudentToGroup(1, 12345678);
		
		assertEquals(false,result);
		
		
	}
	@Test
	public void testSuccesfulCreate (){
		
		Grupo grupo = new Grupo();
		grupo.setId(2);
		grupo.setClave("TST02");
		// Simula lo que harÃ­a el alumnoRepository real cuando le pasan una matricula de alumno
		// que no ha sido guardado
		//when(grupoRepositoryMock.findById(2)).thenReturn(Optional.ofNullable(null));
				
		when(grupoRepositoryMock.save(grupo)).thenReturn(grupo);
				
		// Aqui ejecuto a la unidad que quiero probar
		grupo = grupoService.create(grupo);
				
		// Aqui compruebo el resultado
		assertNotNull(grupo); // Probar que la referencia a alumno no es nula
				
	}
	
	@Test
	public void testSuccesfulretrieveAll() {
		
		Iterable<Grupo> result ;
		result= grupoService.retrieveAll();
				
		// Aqui compruebo el resultado
		assertNotNull(result); // Probar que la referencia a alumno no es nula
		
	}
	
	@Test
	public void testSuccesfulretriveById() {
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setClave("TST01");
		
		// Stubbing para grupoRepository
		when(grupoRepositoryMock.findById(grupo.getId())).thenReturn(Optional.of(grupo));
				
		Grupo result ;
		result= grupoService.retrieve(1);
				
		// Aqui compruebo el resultado
		assertNotNull(result); // Probar que la referencia a alumno no es nula
		
	}
	
	@Test
	public void testSuccesfulDelete() {
		
		boolean delete=grupoService.delete(1);
		assertEquals(false,delete);
	}
}
