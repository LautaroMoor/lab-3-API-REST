package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class AlumnoDaoMemoryImpl implements AlumnoDao {

    private static Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();
    private static final AtomicInteger lastId = new AtomicInteger(0);

    @Override
    public List<Alumno> getAll() throws AlumnoNotFoundException {
        List<Alumno> listaAlumnos = new ArrayList<>();
        for (Map.Entry<Integer, Alumno> entry : repositorioAlumnos.entrySet()) {
            Alumno alumno = entry.getValue();
            listaAlumnos.add(alumno);
        }
        if(listaAlumnos.size() > 0) {
            return listaAlumnos;
        }else{
            throw new AlumnoNotFoundException("No se encontraron alumnos para mostrar una lista");
        }
    }

    @Override
    public Alumno save(Alumno alumno) {
        if (alumno.getId() == 0) {
            alumno.setId(lastId.incrementAndGet());
        }
        return repositorioAlumnos.put(alumno.getId(), alumno);
    }

    @Override
    public Alumno findById(int idAlumno) throws AlumnoNotFoundException {
        for (Alumno a: repositorioAlumnos.values()) {
            if (idAlumno == a.getId()) {
                return a;
            }
        }
        throw new AlumnoNotFoundException("No se encontró un alumno con id " + idAlumno);
    }

    @Override
    public Alumno deleteById(int idAlumno) throws AlumnoNotFoundException {
        for (Alumno a: repositorioAlumnos.values()) {
            if (idAlumno == a.getId()) {
                repositorioAlumnos.remove(a.getId());
                return a;
            }
        }
        throw new AlumnoNotFoundException("No se encontró un alumno con id " + idAlumno);
    }
}
