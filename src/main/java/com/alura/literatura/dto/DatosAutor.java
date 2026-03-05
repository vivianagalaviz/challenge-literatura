package com.alura.literatura.dto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosAutor {
    @JsonAlias("name")
    private String nombre;

    @JsonAlias("birth_year")
    private Integer nacimiento;

    @JsonAlias("death_year")
    private Integer fallecimiento;

    public String getNombre() {
        return nombre;
    }

    public Integer getNacimiento() {
        return nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    @Override
    public String toString() {
        return "Autor: " + nombre +
                " | Nacimiento: " + nacimiento +
                " | Fallecimiento: " + fallecimiento;
    }
}
