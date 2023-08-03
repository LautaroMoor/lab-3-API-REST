package ar.edu.utn.frbb.tup.model.dto;

import ar.edu.utn.frbb.tup.model.Materia;
import ar.edu.utn.frbb.tup.model.Profesor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MateriaDto {
    private String nombre;
    private int anio;
    private int cuatrimestre;
    private long profesorId;
    private int carreraId;
}
