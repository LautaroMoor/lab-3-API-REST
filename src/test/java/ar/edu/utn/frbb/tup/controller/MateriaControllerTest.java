package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.App;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MateriaControllerTest {
    @InjectMocks
    MateriaController materiaController;

    @Mock
    MateriaService materiaService;

    MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();
    }

    @Test
    public void getAllMaterias() throws Exception {
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia("Programacion",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("Lab",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("Matematica",1,1,new Profesor("alfonso","gonzalez","licenciado")));

        Mockito.when(materiaService.getMateriasByNombre("pepe")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materia/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void crearMateriaTest() throws Exception {
        Mockito.when(materiaService.crearMateria(any(MateriaDto.class))).thenReturn(new Materia());
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setAnio(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setNombre("Matematica");
        materiaDto.setProfesorId(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(materiaDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void getAllMateriasByNameTest() throws Exception {
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia("matematica 1",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("matematica 2",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("matematica 3",1,1,new Profesor("alfonso","gonzalez","licenciado")));

        Mockito.when(materiaService.getMateriasByNombre("pepe")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materia")
                        .param("nombre", "matematica")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getMateriaByOrderTest() throws Exception {
        List<Materia> materias = new ArrayList<>();
        materias.add(new Materia("Amatematica",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("Bmatematica",1,1,new Profesor("alfonso","gonzalez","licenciado")));
        materias.add(new Materia("Cmatematica",1,1,new Profesor("alfonso","gonzalez","licenciado")));

        materias.sort(Comparator.comparing(Materia::getNombre));
        Mockito.when(materiaService.getMateriasOrderBy("nombre_asc")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materias")
                        .param("order", "nombre_asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        materias.sort(Comparator.comparing(Materia::getNombre).reversed());
        Mockito.when(materiaService.getMateriasOrderBy("nombre_desc")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materias")
                        .param("order", "nombre_desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        materias.sort(Comparator.comparing(Materia::getMateriaId));
        Mockito.when(materiaService.getMateriasOrderBy("codigo_asc")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materias")
                        .param("order", "codigo_asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        materias.sort(Comparator.comparing(Materia::getMateriaId).reversed());
        Mockito.when(materiaService.getMateriasOrderBy("codigo_desc")).thenReturn(materias);
        mockMvc.perform(MockMvcRequestBuilders.get("/materias")
                        .param("order", "codigo_desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getMateriaByIdTest() throws Exception {
        Materia materia = new Materia("matematica 1",1,1,new Profesor("p","g","l"));
        materia.setMateriaId(123);
        Mockito.when(materiaService.getMateriaById(123)).thenReturn(materia);
        mockMvc.perform(MockMvcRequestBuilders.get("/materia/{idMateria}", 123)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void putMateriaByIdTest() throws Exception {
        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setNombre("matematica 1");

        Materia materia = new Materia();
        materia.setMateriaId(123);
        materia.setNombre(materiaDto.getNombre());

        Mockito.when(materiaService.modificarMateriaById(123, materiaDto)).thenReturn(materia);

        mockMvc.perform(MockMvcRequestBuilders.put("/materia/{idMateria}", 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(materiaDto)))
                .andExpect(status().isOk());
    }

    @Test
    void delMateriaByIdTest() throws Exception {
        Materia materia = new Materia("matematica 1",1,1,new Profesor("alfonso","gonzalez","licenciado"));
        materia.setMateriaId(123);
        Mockito.when(materiaService.borrarMateriaById(123)).thenReturn(materia);

        mockMvc.perform(MockMvcRequestBuilders.delete("/materia/{idMateria}", 123))
                .andExpect(status().isOk());
    }
}
