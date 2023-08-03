package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;

import java.util.List;

public interface MateriaDao {
    List<Materia> getAll() throws MateriaNotFoundException;
    Materia save(Materia materia);
    Materia findById(int idMateria) throws MateriaNotFoundException;
    List<Materia> findAllByName(String name) throws MateriaNotFoundException;
    Materia deleteById(int idMateria) throws MateriaNotFoundException;
    List<Materia> findAllAndOrderBy(String order) throws MateriaNotFoundException, BadOrderByException;
}
