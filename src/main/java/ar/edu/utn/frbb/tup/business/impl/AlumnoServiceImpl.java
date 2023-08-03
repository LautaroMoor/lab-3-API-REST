package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.*;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.persistence.AlumnoDao;
import ar.edu.utn.frbb.tup.persistence.AlumnoDaoMemoryImpl;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlumnoServiceImpl implements AlumnoService {

    private static final AlumnoDao alumnoDao = new AlumnoDaoMemoryImpl();
    //private static final AsignaturaService asignaturaService = new AsignaturaServiceImpl();

    /*
    @Override
    public void aprobarAsignatura(int materiaId, int nota, long dni) throws EstadoIncorrectoException, CorrelatividadesNoAprobadasException {
        Asignatura a = asignaturaService.getAsignatura(materiaId, dni);
        for (Materia m:
             a.getMateria().getCorrelatividades()) {
            Asignatura correlativa = asignaturaService.getAsignatura(m.getMateriaId(), dni);
            if (!EstadoAsignatura.APROBADA.equals(correlativa.getEstado())) {
                throw new CorrelatividadesNoAprobadasException("La materia " + m.getNombre() + " debe estar aprobada para aprobar " + a.getNombreAsignatura());
            }
        }
        a.aprobarAsignatura(nota);
        asignaturaService.actualizarAsignatura(a);
        Alumno alumno = alumnoDao.loadAlumno(dni);
        alumno.actualizarAsignatura(a);
        alumnoDao.save(alumno);
    }*/

    @Override
    public Alumno crearAlumno(AlumnoDto alumno) {
        Alumno a = new Alumno();
        try {
            a.setNombre(alumno.getNombre());
            a.setApellido(alumno.getApellido());
            a.setDni(alumno.getDni());
            alumnoDao.save(a);
        }catch (Exception e){
            e.getStackTrace();
            throw (e);
        }
        return a;
    }

    @Override
    public Alumno buscarAlumno(int idAlumno) throws AlumnoNotFoundException {
        return alumnoDao.findById(idAlumno);
    }

    @Override
    public Alumno modificarAlumnoById(int idAlumno, AlumnoDto alumno) throws AlumnoNotFoundException {
        Alumno a;

        try {
            a = alumnoDao.findById(idAlumno);
            if (alumno.getNombre() != null) {
                a.setNombre(alumno.getNombre());
            }
            if (alumno.getApellido() != null) {
                a.setApellido(alumno.getApellido());
            }
            if (alumno.getDni() != 0) {
                a.setDni(alumno.getDni());
            }
            alumnoDao.save(a);
        }catch (Exception e){
            throw (e);
        }
        return a;
    }

    @Override
    public Alumno borrarAlumnoById(int idAlumno) throws AlumnoNotFoundException {
        return alumnoDao.deleteById(idAlumno);
    }

    @Override
    public List<Alumno> getAlumnos() throws AlumnoNotFoundException {
        return alumnoDao.getAll();
    }
}
