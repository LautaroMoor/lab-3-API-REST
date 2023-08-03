package ar.edu.utn.frbb.tup.persistence;

import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CarreraDaoMemoryImpl implements CarreraDao{

    private static final Map<Integer, Carrera> repositorioCarrera = new HashMap<>();
    private static final AtomicInteger lastId = new AtomicInteger(0);

    @Override
    public List<Carrera> getAll() throws CarreraNotFoundException {
        List<Carrera> listaCarreras = new ArrayList<>();
        for (Map.Entry<Integer, Carrera> entry : repositorioCarrera.entrySet()) {
            Carrera carrera = entry.getValue();
            listaCarreras.add(carrera);
        }
        if(listaCarreras.size() > 0) {
            return listaCarreras;
        }else{
            throw new CarreraNotFoundException("No se encontraron carreras para mostrar una lista");
        }
    }

    @Override
    public Carrera save(Carrera carrera) {
        if (carrera.getId() == 0) {
            carrera.setId(lastId.incrementAndGet());
        }
        repositorioCarrera.put(carrera.getId(), carrera);
        return carrera;
    }

   @Override
    public Carrera findById(int idCarrera) throws CarreraNotFoundException {
        for (Carrera c:
                repositorioCarrera.values()) {
            if (idCarrera == c.getId()) {
                return c;
            }
        }
        throw new CarreraNotFoundException("No se encontró la carrera con id " + idCarrera);
    }

    @Override
    public Carrera deleteById(int idCarrera) throws CarreraNotFoundException {
        for (Carrera c: repositorioCarrera.values()) {
            if (idCarrera == c.getId()) {
                repositorioCarrera.remove(c.getId());
                return c;
            }
        }
        throw new CarreraNotFoundException("No se encontró la carrera con id " + idCarrera);
    }
}