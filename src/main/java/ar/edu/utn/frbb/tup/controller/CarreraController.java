package ar.edu.utn.frbb.tup.controller;

import ar.edu.utn.frbb.tup.business.CarreraService;
import ar.edu.utn.frbb.tup.model.Carrera;
import ar.edu.utn.frbb.tup.model.dto.CarreraDto;
import ar.edu.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carrera")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    //    POST: /carrera
    //    PUT: /carrera/{idCarrera}
    //    DELETE: /carrera/{idCarrera}

    @GetMapping
    public List<Carrera> getCarreras() throws CarreraNotFoundException {
        return carreraService.getCarreras();
    }
    @PostMapping
    public Carrera crearCarrera(@RequestBody CarreraDto carreraDto) {
        return carreraService.crearCarrera(carreraDto);
    }

    @PutMapping("/{idCarrera}")
    public Carrera modificarCarreraById(@PathVariable Integer idCarrera,
                                        @RequestBody CarreraDto carreraDto) throws CarreraNotFoundException {
        return carreraService.modificarCarreraById(idCarrera,carreraDto);
    }

    @DeleteMapping("/{idCarrera}")
    public Carrera borrarCarreraById(@PathVariable Integer idCarrera) throws CarreraNotFoundException {
        return carreraService.borrarCarreraById(idCarrera);
    }
}
