package ar.edu.utn.frbb.tup.business;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;

import java.util.List;

public interface MateriaService {
    Materia crearMateria(MateriaDto inputData) throws Exception;
    List<Materia> getAllMaterias() throws MateriaNotFoundException;
    Materia getMateriaById(int idMateria) throws MateriaNotFoundException;
    Materia modificarMateriaById(int idMateria,MateriaDto materia) throws MateriaNotFoundException, ProfesorNotFoundException;
    Materia borrarMateriaById(int idMateria) throws MateriaNotFoundException;
    List<Materia> getMateriasByNombre(String nombre) throws MateriaNotFoundException;
    List<Materia> getMateriasOrderBy(String orderBy) throws MateriaNotFoundException, BadOrderByException;

}
