package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.App;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.business.impl.MateriaServiceImpl;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {
    @Mock
    private MateriaDao dao;
    private ProfesorService profesorService;
    private CarreraDao carreraDao;
    @InjectMocks
    private MateriaService materiaService;

    @BeforeEach
    public void setUp(){
        Materia materia1 = new Materia("Lautaro",1,2,new Profesor("Luciano","Salotto","Licenciado"));
        Materia materia2 = new Materia("Gabriel",1,2,new Profesor("Luciano","Salotto","Licenciado"));
        dao.save(materia1);
        dao.save(materia2);
    }

    @Test
    public void getAll(){
    }

}
