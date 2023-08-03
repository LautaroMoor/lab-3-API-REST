package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.List;

public interface CarreraDao {
    List<Carrera> getAll() throws CarreraNotFoundException;
    Carrera save(Carrera carrera);
    Carrera findById(int idCarrera) throws CarreraNotFoundException;
    Carrera deleteById(int idCarrera) throws CarreraNotFoundException;
}
