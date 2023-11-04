package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.AsignaturaInexistenteException;
import ar.edu.utn.frbb.tup.model.exception.CorrelatividadesNoAprobadasException;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;

import java.util.List;

public interface AlumnoService {
    //void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException;

    Alumno crearAlumno(AlumnoDto alumno);

    Alumno buscarAlumno(int idAlumno) throws AlumnoNotFoundException ;

    Alumno modificarAlumnoById(int idAlumno, AlumnoDto alumno) throws AlumnoNotFoundException;

    Alumno borrarAlumnoById(int idAlumno) throws AlumnoNotFoundException;

    List<Alumno> getAlumnos()throws AlumnoNotFoundException;

    Alumno modficarEAAsignaturaByAlumno(int idAlumno, int idAsignatura, int nota) throws AlumnoNotFoundException, AsignaturaInexistenteException;
}
