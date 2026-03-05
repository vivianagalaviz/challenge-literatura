package com.alura.literatura.model;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer gutenbergId;

    @Column(nullable = false)
    private String titulo;

    private String idioma;
    private Integer descargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(Integer gutenbergId, String titulo, String idioma, Integer descargas, Autor autor) {
        this.gutenbergId = gutenbergId;
        this.titulo = titulo;
        this.idioma = idioma;
        this.descargas = descargas;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public Integer getGutenbergId() { return gutenbergId; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Integer getDescargas() { return descargas; }
    public Autor getAutor() { return autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : "N/A") +
                ", idioma='" + idioma + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
