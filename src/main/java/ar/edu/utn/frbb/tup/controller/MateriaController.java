package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.MateriaService;
import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.dto.MateriaDto;
import ar.edu.utn.frbb.tup.persistence.exception.BadOrderByException;
import ar.edu.utn.frbb.tup.persistence.exception.MateriaNotFoundException;
import ar.edu.utn.frbb.tup.persistence.exception.ProfesorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/materia/getAll") //Tiene test
    public List<Materia> getMaterias() throws MateriaNotFoundException {
        return materiaService.getAllMaterias();
    }

    @GetMapping("/materia") //Tiene test
    public List<Materia> getMateriasByNombre(@RequestParam("nombre") String nombre) throws MateriaNotFoundException {
        return materiaService.getMateriasByNombre(nombre);
    }

    @GetMapping("/materias") //Tiene test
    public List<Materia> getMateriasOrderBy(@RequestParam("order") String order) throws MateriaNotFoundException, BadOrderByException {
        return materiaService.getMateriasOrderBy(order);
    }

    @PostMapping("/materia") //Tiene test
    public Materia crearMateria(@RequestBody MateriaDto materiaDto) throws Exception {
        return materiaService.crearMateria(materiaDto);
    }

    @GetMapping("/materia/{idMateria}") //Tiene test
    public Materia getMateriaById(@PathVariable Integer idMateria) throws MateriaNotFoundException {
        return materiaService.getMateriaById(idMateria);
    }

    @PutMapping("/materia/{idMateria}") //Tiene test
    public Materia modificarMateriaById(@PathVariable Integer idMateria,
                                        @RequestBody MateriaDto materiaDto) throws MateriaNotFoundException, ProfesorNotFoundException {
        return materiaService.modificarMateriaById(idMateria,materiaDto);
    }

    @DeleteMapping("/materia/{idMateria}") //Tiene test
    public Materia borrarMateriaById(@PathVariable Integer idMateria) throws MateriaNotFoundException {
        return materiaService.borrarMateriaById(idMateria);
    }
}
