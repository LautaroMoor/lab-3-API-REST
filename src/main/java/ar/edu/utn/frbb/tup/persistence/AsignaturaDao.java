package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Asignatura;

import java.util.List;

public interface AsignaturaDao {
    public void save(Asignatura asignatura);
    public List<Asignatura> getAll();
}
