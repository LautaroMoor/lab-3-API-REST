package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;

import java.util.List;

public interface AlumnoDao {
    List<Alumno> getAll() throws AlumnoNotFoundException;
    Alumno save(Alumno a);
    Alumno findById(int idAlumno) throws AlumnoNotFoundException;
    Alumno deleteById(int idAlumno) throws AlumnoNotFoundException;
}
