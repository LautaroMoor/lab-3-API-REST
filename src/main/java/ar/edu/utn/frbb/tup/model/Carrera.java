package ar.edu.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Carrera {
    private int id;
    private String nombre;
    private int cantidadCuatrimestres;
    private int idDepartamento;
    private List<Materia> materiasList = new ArrayList<>();

    public Carrera(String nombre, int cantidadCuatrimestres, int idDepartamento) {
        this.nombre = nombre;
        this.cantidadCuatrimestres = cantidadCuatrimestres;
        this.idDepartamento = idDepartamento;
    }

    public Carrera() {

    }

    public void agregarMateria(Materia materia) {
        materiasList.add(materia);
    }
}
