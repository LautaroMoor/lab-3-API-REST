package ar.edu.utn.frbb.tup.model;

import ar.edu.utn.frbb.tup.model.exception.EstadoIncorrectoException;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.Random;

public class Asignatura {
    private Materia materia;
    private EstadoAsignatura estado;
    private Integer nota;

    public Asignatura() {
    }
    public Asignatura(Materia materia) {
        Random random = new Random();
        this.materia = materia;
        this.estado = EstadoAsignatura.NO_CURSADA;
    }


    public Optional<Integer> getNota() {
        return Optional.ofNullable(nota);
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public String getNombreAsignatura(){
        return this.materia.getNombre();
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void cursarAsignatura(){
        this.estado = EstadoAsignatura.CURSADA;
    }

    public void asignarEstadoMateria (Long nota){
        if (nota >= 8){
            setEstado(EstadoAsignatura.APROBADA);
        }else {
            if (nota <8 && nota<=6)
                setEstado(EstadoAsignatura.CURSADA);
            else
                setEstado(EstadoAsignatura.NO_CURSADA);
        }
    }

    public void aprobarAsignatura(int nota) throws EstadoIncorrectoException {
        if (!this.estado.equals(EstadoAsignatura.CURSADA)) {
            throw new EstadoIncorrectoException("La materia debe estar cursada");
        }
        if (nota>=4) {
            this.estado = EstadoAsignatura.APROBADA;
            this.nota = nota;
        }
    }

}
