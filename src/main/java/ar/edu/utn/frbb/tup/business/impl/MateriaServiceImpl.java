package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.AsignaturaService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.business.ProfesorService;
import ar.edu.utn.frbb.tup.model.Asignatura;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.AsignaturaDao;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {
    private final MateriaDao dao;
    private final ProfesorService profesorService;
    private final CarreraDao carreraDao;
    private final AsignaturaService asignaturaService;

    @Autowired
    public MateriaServiceImpl(MateriaDao dao, ProfesorService profesorService, CarreraDao carreraDao, AsignaturaService asignaturaService) {
        this.dao = dao;
        this.profesorService = profesorService;
        this.carreraDao = carreraDao;
        this.asignaturaService = asignaturaService;
    }

    @Override
    public Materia crearMateria(MateriaDto materia) throws Exception {
        Materia m = new Materia();
        try {
            m.setNombre(materia.getNombre());
            m.setAnio(materia.getAnio());
            m.setCuatrimestre(materia.getCuatrimestre());
            Profesor profesor = profesorService.buscarProfesor(materia.getProfesorId());
            if (profesor == null) {
                throw new ProfesorNotFoundException("No se encontro un profesor con esa id");
            }else {
                m.setProfesor(profesor);
            }

            //========================
            //Buscar y guardar carrera
            //========================
            int debug = materia.getCarreraId();
            if (debug != 0) {
                Carrera carrera = carreraDao.findById(materia.getCarreraId());
                carrera.agregarMateria(m);
                carreraDao.save(carrera);
                m.setIdCarrera(carrera.getId());
            }
            //========================

            asignaturaService.crearAsignatura(m);
            dao.save(m);
        }catch (Exception e){
            e.getStackTrace();
            throw (e);
        }
        return m;
    }

    @Override
    public List<Materia> getAllMaterias() throws MateriaNotFoundException {
        return dao.getAll();
    }

    @Override
    public Materia getMateriaById(int idMateria) throws MateriaNotFoundException {
        return dao.findById(idMateria);
    }

    @Override
    public Materia modificarMateriaById(int idMateria, MateriaDto materia) throws MateriaNotFoundException, ProfesorNotFoundException {
        Materia m;

        try {
            m = dao.findById(idMateria);
            if (m.getMateriaId() == 0){
                throw new MateriaNotFoundException("Materia no encontrada");
            }
            m.setNombre(materia.getNombre());
            m.setAnio(materia.getAnio());
            m.setCuatrimestre(materia.getCuatrimestre());
            Profesor profesor = profesorService.buscarProfesor(materia.getProfesorId());
            if (profesor == null) {
                throw new ProfesorNotFoundException("Profesor no encontrado");
            } else {
                m.setProfesor(profesor);
            }
            dao.save(m);
        }catch (Exception e){
            throw (e);
        }
        return m;
    }

    @Override
    public Materia borrarMateriaById(int idMateria) throws MateriaNotFoundException {
        Materia m = null;
        try{
            m = dao.findById(idMateria);
            if (m.getMateriaId() == 0){
                throw new MateriaNotFoundException("Materia no encontrada");
            }
            dao.deleteById(m.getMateriaId());
        }catch (Exception e){
            throw (e);
        }
        return m;
    }

    @Override
    public List<Materia> getMateriasByNombre(String nombre) throws MateriaNotFoundException {
        return dao.findAllByName(nombre);
    }

    @Override
    public List<Materia> getMateriasOrderBy(String orderBy) throws MateriaNotFoundException, BadOrderByException {
        return dao.findAllAndOrderBy(orderBy);
    }
}
