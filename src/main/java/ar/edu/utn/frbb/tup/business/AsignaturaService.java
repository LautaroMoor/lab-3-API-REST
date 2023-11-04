package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Materia;

import java.util.List;

public interface AsignaturaService {
    void crearAsignatura(Materia m);
    List<Asignatura> getAll();
}
