package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarreraServiceImplTest {
    @Mock
    private CarreraDao dao;

    @InjectMocks
    private CarreraServiceImpl carreraService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCarreras() throws CarreraNotFoundException {
        List<Carrera> carreras = dao.getAll();
        Mockito.when(carreraService.getCarreras()).thenReturn(carreras);
    }

    @Test
    void crearCarrera() {
        Carrera carrera = new Carrera("Lautaro",1,2);
        dao.save(carrera);
    }

    @Test
    void modificarCarreraById() throws CarreraNotFoundException {
        Carrera carrera = new Carrera("carrera 1", 1,2);
        carrera.setId(123);
        dao.save(carrera);
        Mockito.when(dao.findById(123)).thenReturn(carrera);
        Carrera carreraEncontrada = dao.findById(123);
        assertEquals(carrera, carreraEncontrada);

        CarreraDto carreraDto = new CarreraDto();
        carreraDto.setNombre("carrera 2");
        carreraDto.setId(2);
        carreraDto.setCantidadCuatrimestres(2);

        carreraService.modificarCarreraById(carrera.getId(),carreraDto);
        carrera.setNombre(carreraDto.getNombre());
        assertEquals(carrera.getNombre(), carreraDto.getNombre());
    }
}