package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MateriaServiceImplTest {

    @Mock
    private MateriaDao dao;

    @Mock
    private ProfesorService profesorService;

    @InjectMocks
    private MateriaServiceImpl materiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearMateria() {
        Materia materia = new Materia("materia 1", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        materia.setMateriaId(1);
        dao.save(materia);
    }

    @Test
    void getAllMaterias() throws MateriaNotFoundException {
        List<Materia> materias = dao.getAll();
        Mockito.when(materiaService.getAllMaterias()).thenReturn(materias);
    }

    @Test
    void getMateriaById() throws MateriaNotFoundException {
        Materia materia = new Materia("materia 1", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        materia.setMateriaId(1);
        dao.save(materia);
        Mockito.when(dao.findById(1)).thenReturn(materia);
        Materia materiaEncontrada = materiaService.getMateriaById(1);
        assertEquals(materia, materiaEncontrada);
    }

    @Test
    void modificarMateriaById() throws MateriaNotFoundException, ProfesorNotFoundException {
        Profesor pA = new Profesor("a", "g", "L");
        Profesor pB = new Profesor("b", "g", "L");
        Profesor pC = new Profesor("c", "g", "L");

        Mockito.when(profesorService.buscarProfesor(2)).thenReturn(pB);

        List<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia("Physics", 1, 1, pA);
        Materia materia2 = new Materia("Math", 2, 1, pB);
        Materia materia3 = new Materia("Chemistry", 3, 1, pC);
        materia1.setMateriaId(1);
        materia2.setMateriaId(2);
        materia2.getCorrelatividades().add(materia2);
        materia3.setMateriaId(3);
        materia3.getCorrelatividades().add(materia1);
        materia3.getCorrelatividades().add(materia3);
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);

        dao.save(materia1);
        Mockito.when(dao.findById(1)).thenReturn(materia1);
        Materia materiaEncontrada = materiaService.getMateriaById(1);
        assertEquals(materia1, materiaEncontrada);

        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setAnio(1);
        materiaDto.setProfesorId(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setNombre("Math");

        materia1.setAnio(materiaDto.getAnio());
        materia1.setCuatrimestre(materiaDto.getCuatrimestre());
        materia1.setNombre(materiaDto.getNombre());
        Mockito.when(profesorService.buscarProfesor(1)).thenReturn(pA);
        Mockito.when(profesorService.buscarProfesor(2)).thenReturn(pB);
        materia1.setProfesor(profesorService.buscarProfesor(materiaDto.getProfesorId()));
        assertEquals(materia1.getNombre(), materiaDto.getNombre());

        Mockito.when(dao.getAll()).thenReturn(materias);


        materiaService.modificarMateriaById(materia1.getMateriaId(), materiaDto);
    }

    @Test
    void borrarMateriaById() throws MateriaNotFoundException {
        Profesor pA = new Profesor("Lautaro", "Moor", "Magic Hands :)");
        Profesor pB = new Profesor("A", "Moor", "Magic Hands :)");
        Profesor pC = new Profesor("B", "Moor", "Magic Hands :)");

        Mockito.when(profesorService.buscarProfesor(2)).thenReturn(pB);

        List<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia("Physics", 1, 1, pA);
        Materia materia2 = new Materia("Math", 2, 1, pB);
        Materia materia3 = new Materia("Chemistry", 3, 1, pC);
        materia1.setMateriaId(1);
        materia2.setMateriaId(2);
        materia2.getCorrelatividades().add(materia2);
        materia3.setMateriaId(3);
        materia3.getCorrelatividades().add(materia1);
        materia3.getCorrelatividades().add(materia3);
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);

        dao.save(materia1);
        Mockito.when(dao.findById(1)).thenReturn(materia1);
        Materia materiaEncontrada = materiaService.getMateriaById(1);
        assertEquals(materia1, materiaEncontrada);

        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setAnio(1);
        materiaDto.setProfesorId(1);
        materiaDto.setCuatrimestre(1);
        materiaDto.setNombre("Math");

        materia1.setAnio(materiaDto.getAnio());
        materia1.setCuatrimestre(materiaDto.getCuatrimestre());
        materia1.setNombre(materiaDto.getNombre());
        Mockito.when(profesorService.buscarProfesor(1)).thenReturn(pA);
        Mockito.when(profesorService.buscarProfesor(2)).thenReturn(pB);
        materia1.setProfesor(profesorService.buscarProfesor(materiaDto.getProfesorId()));
        assertEquals(materia1.getNombre(), materiaDto.getNombre());


        materiaService.borrarMateriaById(1);
        assertThrows(MateriaNotFoundException.class, () -> materiaService.borrarMateriaById(4));
    }

    @Test
    void getMateriasByNombre() throws MateriaNotFoundException {
        Map<Integer, Materia> materiasMap = new HashMap<>();
        List<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia("materia1", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        Materia materia2 = new Materia("materia123", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        Materia materia3 = new Materia("Lautaro1", 1, 1, new Profesor("Lautaro", "Moor", "Magic Hands :)"));
        materiasMap.put(1, materia1);
        materiasMap.put(2, materia2);
        materiasMap.put(3, materia3);
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        Mockito.when(dao.getAll()).thenReturn(materias);

        String nombre = "materia1";
        List<Materia> materiasEncontradas = new ArrayList<>();
        for (Materia materia : materiasMap.values()) {
            if (materia.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                materiasEncontradas.add(materia);
            }
        }
        if (materiasEncontradas.size() > 0) {
            assertDoesNotThrow(() -> materiaService.getMateriasByNombre(nombre));
        }
        else {
            assertThrows(MateriaNotFoundException.class, () -> materiaService.getMateriasByNombre(nombre));
        }
    }
}