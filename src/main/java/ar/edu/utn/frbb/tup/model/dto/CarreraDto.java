package ar.edu.utn.frbb.tup.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDto {
    private int id;
    private String nombre;
    private int cantidadCuatrimestres;
    private int idDepartamento;
}
