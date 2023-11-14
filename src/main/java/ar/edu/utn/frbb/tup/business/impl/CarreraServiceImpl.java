package ar.edu.utn.frbb.tup.business.impl;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.CarreraDao;
import ar.edu.utn.frbb.tup.persistence.MateriaDao;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {
    @Autowired
    private CarreraDao dao;

    @Override
    public List<Carrera> getCarreras() throws CarreraNotFoundException {
        return dao.getAll();
    }

    @Override
    public Carrera crearCarrera(CarreraDto carrera) {
        Carrera c = new Carrera();
        try {
            c.setNombre(carrera.getNombre());
            c.setIdDepartamento(carrera.getIdDepartamento());
            c.setCantidadCuatrimestres(carrera.getCantidadCuatrimestres());
            dao.save(c);
        }catch (Exception e){
            e.getStackTrace();
            throw (e);
        }
        return c;
    }

    @Override
    public Carrera modificarCarreraById(Integer idCarrera,CarreraDto carrera) throws CarreraNotFoundException {
        Carrera c;

        try {
            c = dao.findById(idCarrera);
            if (carrera.getNombre() != null) {
                c.setNombre(carrera.getNombre());
            }
            if (carrera.getIdDepartamento() != 0) {
                c.setIdDepartamento(carrera.getIdDepartamento());
            }
            if (carrera.getCantidadCuatrimestres() != 0) {
                c.setCantidadCuatrimestres(carrera.getCantidadCuatrimestres());
            }
            dao.save(c);
        }catch (Exception e){
            throw (e);
        }
        return c;
    }

    @Override
    public Carrera borrarCarreraById(Integer idCarrera) throws CarreraNotFoundException {
        Carrera c = null;
        try{
            c = dao.findById(idCarrera);
            if (c == null || c.getId() == 0){
                throw new CarreraNotFoundException("Carrera no encontrada");
            }
            dao.deleteById(c.getId());
        }catch (Exception e){
            throw (e);
        }
        return c;
    }
}
