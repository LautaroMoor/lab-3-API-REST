package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MateriaDaoMemoryImpl implements MateriaDao {

    private static final Map<Integer, Materia> repositorioMateria = new HashMap<>();
    private static final AtomicInteger lastId = new AtomicInteger(0);

    @Override
    public List<Materia> getAll() throws MateriaNotFoundException {
        List<Materia> listaMaterias = new ArrayList<>();
        for (Map.Entry<Integer, Materia> entry : repositorioMateria.entrySet()) {
            Materia materia = entry.getValue();
            listaMaterias.add(materia);
        }
        if(listaMaterias.size() > 0) {
            return listaMaterias;
        }else{
            throw new MateriaNotFoundException("No se encontraron materias para mostrar una lista");
        }
    }

    @Override
    public Materia save(Materia materia) {
        if (materia.getMateriaId() == 0) {
            materia.setMateriaId(lastId.incrementAndGet());
        }
        repositorioMateria.put(materia.getMateriaId(), materia);
        return materia;
    }

    @Override
    public Materia findById(int idMateria) throws MateriaNotFoundException {
        for (Materia m:
             repositorioMateria.values()) {
            if (idMateria == m.getMateriaId()) {
                return m;
            }
        }
        throw new MateriaNotFoundException("No se encontró la materia con id " + idMateria);
    }

    @Override
    public Materia deleteById(int idMateria) throws MateriaNotFoundException {
        for (Materia m: repositorioMateria.values()) {
            if (idMateria == m.getMateriaId()) {
                repositorioMateria.remove(m.getMateriaId());
                return m;
            }
        }
        throw new MateriaNotFoundException("No se encontró la materia con id " + idMateria);
    }

    @Override
    public List<Materia> findAllByName(String nombre) throws MateriaNotFoundException {
        List<Materia> listaMaterias = new ArrayList<>();
        for (Materia m:
                repositorioMateria.values()) {
            if (m.getNombre().contains(nombre)) {
                listaMaterias.add(m);
            }
        }
        if(listaMaterias.size() > 0){
            return listaMaterias;
        }else {
            throw new MateriaNotFoundException("No se encontró ninguna materia con el " + nombre);
        }
    }


    @Override
    public List<Materia> findAllAndOrderBy(String order) throws MateriaNotFoundException, BadOrderByException {
        List<Materia> listaMaterias = this.getAll();
        // Obtengo order, como es un string, lo divido en dos en un arreglo y asi lo guardo en dos variables distintas
        String[] orderParts = order.split("_");
        String campoOrden = orderParts[0];
        String tipoOrden = orderParts[1];

        // Ordenar la lista de materias
        if (campoOrden.equalsIgnoreCase("nombre")) {
            if (tipoOrden.equalsIgnoreCase("asc")) {
                listaMaterias.sort(Comparator.comparing(Materia::getNombre));
            } else if (tipoOrden.equalsIgnoreCase("desc")) {
                listaMaterias.sort(Comparator.comparing(Materia::getNombre).reversed());
            }else {
                throw new BadOrderByException("OrderBy mal hecho");
            }
        } else if (campoOrden.equalsIgnoreCase("codigo")) {
            if (tipoOrden.equalsIgnoreCase("asc")) {
                listaMaterias.sort(Comparator.comparing(Materia::getMateriaId));
            } else if (tipoOrden.equalsIgnoreCase("desc")) {
                listaMaterias.sort(Comparator.comparing(Materia::getMateriaId).reversed());
            }else{
                throw new BadOrderByException("OrderBy mal hecho");
            }
        }else{
            throw new BadOrderByException("OrderBy mal hecho");
        }

        if (listaMaterias.size()>0)
            return listaMaterias;
        else
            throw new MateriaNotFoundException("No se encontró ninguna materia");
    }

}
