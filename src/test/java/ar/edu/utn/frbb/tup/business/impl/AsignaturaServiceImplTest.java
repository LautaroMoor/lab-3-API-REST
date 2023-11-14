package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsignaturaServiceImplTest {
    @Mock
    private AsignaturaDao dao;

    @InjectMocks
    private AsignaturaServiceImpl alumnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void crearAsignatura() {
        Materia materia = new Materia("materia 1", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        materia.setMateriaId(1);
        Asignatura asignatura = new Asignatura(materia);
        dao.save(asignatura);
    }

    @Test
    void getAll() {
        List<Asignatura> asignaturas = dao.getAll();
        Mockito.when(dao.getAll()).thenReturn(asignaturas);
    }
}