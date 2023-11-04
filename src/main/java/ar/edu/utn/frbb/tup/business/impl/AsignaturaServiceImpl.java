package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.EstadoAsignatura;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import ar.edu.utn.frbb.tup.persistence.*;
import ar.edu.utn.frbb.tup.persistence.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
    private final AsignaturaDao dao;
    @Autowired
    public AsignaturaServiceImpl(AsignaturaDao dao) {
        this.dao = dao;
    }

    @Override
    public void crearAsignatura(Materia m) {
        Asignatura asignatura = new Asignatura();
        asignatura.setMateria(m);
        asignatura.setEstado(EstadoAsignatura.NO_CURSADA);
        dao.save(asignatura);
    }

    @Override
    public List<Asignatura> getAll() {
        return dao.getAll();
    }
}
