package ar.edu.utn.frbb.tup.controller;
import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.AsignaturaInexistenteException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class AlumnoControllerTest {

    @InjectMocks
    AlumnoController alumnoController;

    @Mock
    AlumnoService alumnoService;

    MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
    }

    @Test
    void crearAlumno() throws Exception {
        Mockito.when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn(new Alumno());
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Lautaro");
        alumnoDto.setApellido("Moor");
        mockMvc.perform(MockMvcRequestBuilders.post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(alumnoDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void getAllAlumnos() throws Exception {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Lautaro","Moor",2044595433));
        alumnos.add(new Alumno("Maxi","Garcia",2042323456));
        alumnos.add(new Alumno("Lionel","Messi",234532553));

        Mockito.when(alumnoService.getAlumnos()).thenReturn(alumnos);
        mockMvc.perform(MockMvcRequestBuilders.get("/alumno")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void putAlumnoById() throws Exception {
        AlumnoDto alumnoDto = new AlumnoDto();
        alumnoDto.setNombre("Lautaro");
        alumnoDto.setApellido("Moor");

        Alumno alumno = new Alumno("Maxi","Garcia",123423142);
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setId(123);

        Mockito.when(alumnoService.modificarAlumnoById(123, alumnoDto)).thenReturn(alumno);

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/{idAlumno}", 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(alumnoDto)))
                .andExpect(status().isOk());
    }

    @Test
    void delAlumnoById() throws Exception {
        Alumno alumno = new Alumno("Lautaro","Moor",113123123);
        alumno.setId(123);
        Mockito.when(alumnoService.borrarAlumnoById(123)).thenReturn(alumno);

        mockMvc.perform(MockMvcRequestBuilders.delete("/alumno/{idAlumno}", 123))
                .andExpect(status().isOk());
    }

    @Test
    void putModficarEAAsignaturaByAlumno() throws Exception, AsignaturaInexistenteException {
        int nota = 10;

        Asignatura asignatura = new Asignatura(new Materia());
        asignatura.getMateria().setMateriaId(1);

        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setAsignaturas(new ArrayList<>());
        alumno.getAsignaturas().add(asignatura);

        Mockito.when(alumnoService.modficarEAAsignaturaByAlumno(1,1, nota)).thenReturn(alumno);

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/{idAlumno}/asignatura/{idAsignatura}", 123,1)
                        .param("nota", String.valueOf(nota))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(alumno)))
                .andExpect(status().is2xxSuccessful());
    }
}
