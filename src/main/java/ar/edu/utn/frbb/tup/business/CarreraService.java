package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.List;

public interface CarreraService {
    List<Carrera> getCarreras() throws CarreraNotFoundException;
    Carrera crearCarrera(CarreraDto carrera);
    Carrera modificarCarreraById(Integer idCarrera, CarreraDto carrera) throws CarreraNotFoundException;
    Carrera borrarCarreraById(Integer idCarrera) throws CarreraNotFoundException;
}
