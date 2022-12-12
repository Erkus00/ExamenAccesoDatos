package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Libro implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "titulo", nullable = false, length = 128)
    private String titulo;
    @Basic
    @Column(name = "autor", nullable = false, length = 128)
    private String autor;

    /*
    Completar con los métodos y atributos que sean necesarios
    */

    @Transient
    private ArrayList<Ejemplar> ejemplars;

    public Libro() {
    }

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<Ejemplar> getEjemplars() {
        return ejemplars;
    }

    public void setEjemplars(ArrayList<Ejemplar> ejemplars) {
        this.ejemplars = ejemplars;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }


}
