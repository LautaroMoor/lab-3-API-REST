package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.AlumnoService;
import ar.edu.utn.frbb.tup.model.Alumno;
import ar.edu.utn.frbb.tup.model.dto.AlumnoDto;
import ar.edu.utn.frbb.tup.model.exception.AsignaturaInexistenteException;
import ar.edu.utn.frbb.tup.persistence.exception.AlumnoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping // Test hecho
    public List<Alumno> getAlumnos () throws AlumnoNotFoundException {
        return alumnoService.getAlumnos();
    }

    @PostMapping // Test hecho
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.crearAlumno(alumnoDto);
    }

    @PutMapping("/{idAlumno}") // Test hecho
    public Alumno modificarAlumnoById(@PathVariable Integer idAlumno,
                                      @RequestBody AlumnoDto alumno) throws AlumnoNotFoundException {
        return alumnoService.modificarAlumnoById(idAlumno,alumno);
    }

    @DeleteMapping("/{idAlumno}") // Test hecho
    public Alumno borrarAlumnoById(@PathVariable Integer idAlumno) throws AlumnoNotFoundException {
        return alumnoService.borrarAlumnoById(idAlumno);
    }

    @PutMapping("/{idAlumno}/asignatura/{idAsignatura}") //FALTA HACER ESTE
    public Alumno modficarEAAsignaturaByAlumno(@PathVariable Integer idAlumno,
                                                 @PathVariable Integer idAsignatura,
                                                 @RequestParam Integer nota) throws AlumnoNotFoundException, AsignaturaInexistenteException {
        return alumnoService.modficarEAAsignaturaByAlumno(idAlumno, idAsignatura, nota);
    }
 }
