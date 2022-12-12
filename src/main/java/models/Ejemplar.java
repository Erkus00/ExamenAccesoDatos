package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ejemplar implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "estado", nullable = false, length = 16)
    private String estado; /* excelente, bueno, regular, malo */

    @Basic
    @Column(name = "edicion", nullable = false)
    private Integer edicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    public Libro libro_asociado;


    public Ejemplar() {
    }

    public Ejemplar(String estado, Integer edicion) {
        this.estado = estado;
        this.edicion = edicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public Libro getLibro_asociado() {
        return libro_asociado;
    }

    public void setLibro_asociado(Libro libro_asociado) {
        this.libro_asociado = libro_asociado;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "id=" + id + ", estado=" + estado + ", edicion=" + edicion + '}';
    }


}
