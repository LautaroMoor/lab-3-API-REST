package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
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
public class CarreraControllerTest {
    @InjectMocks
    CarreraController carreraController;

    @Mock
    CarreraService carreraService;

    MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(carreraController).build();
    }

    @Test
    void crearCarrera() throws Exception {
        Mockito.when(carreraService.crearCarrera(any(CarreraDto.class))).thenReturn(new Carrera());
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("TUP");

        mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(carreraDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void modificarCarreraById() throws Exception {
        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("TUP");

        Carrera carrera = new Carrera();
        carrera.setId(123);
        carrera.setNombre(carreraDto.getNombre());

        Mockito.when(carreraService.modificarCarreraById(123,carreraDto)).thenReturn(carrera);

        mockMvc.perform(MockMvcRequestBuilders.put("/carrera/{idCarrera}", 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(carreraDto)))
                .andExpect(status().isOk());
    }

    @Test
    void borrarCarreraById() throws Exception {
        Carrera carrera = new Carrera();
        carrera.setId(123);
        Mockito.when(carreraService.borrarCarreraById(123)).thenReturn(carrera);
        mockMvc.perform(MockMvcRequestBuilders.delete("/carrera/{idCarrera}", 123)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCarreras() throws Exception {
        List<Carrera> carreras = new ArrayList<>();
        carreras.add(new Carrera("TUP 1",1,1,1));
        carreras.add(new Carrera("TUP 2",1,1,2));
        carreras.add(new Carrera("TUP 3",1,1,3));

        Mockito.when(carreraService.getCarreras()).thenReturn(carreras);
        mockMvc.perform(MockMvcRequestBuilders.get("/carrera")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
