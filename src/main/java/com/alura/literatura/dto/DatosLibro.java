package com.alura.literatura.dto;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public class DatosLibro {
    @JsonAlias("id")
    private Integer id;

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DatosAutor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Integer descargas;

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<DatosAutor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nAutor: " + autores +
                "\nIdioma: " + idiomas +
                "\nDescargas: " + descargas;
    }
}
