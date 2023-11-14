package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoServiceImplTest {
    @Mock
    private AlumnoDao dao;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void crearAlumno() {
        Alumno alumno = new Alumno("Lautaro","Moor",44490092);
        dao.save(alumno);
    }

    @Test
    void buscarAlumno() throws AlumnoNotFoundException {
        Alumno alumno = new Alumno("Lautaro","Moor",44490092);
        alumno.setId(123);
        dao.save(alumno);
        Mockito.when(dao.findById(123)).thenReturn(alumno);
    }

    @Test
    void getAlumnos() throws AlumnoNotFoundException {
        List<Alumno> alumnos = new ArrayList<>(dao.getAll());
        if (alumnos.size()==0) {
            assertThrows(AlumnoNotFoundException.class, () -> alumnoService.getAlumnos());
        }
        else {
            Mockito.when(alumnoService.getAlumnos()).thenReturn(alumnos);
        }
    }
}