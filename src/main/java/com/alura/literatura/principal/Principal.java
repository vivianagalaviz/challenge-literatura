package com.alura.literatura.principal;
import com.alura.literatura.dto.DatosLibro;
import com.alura.literatura.dto.DatosRespuesta;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    
                    Elija una opción:
                    1 - Buscar libro por título (API)
                    0 - Salir
                    """);

            // Evita que se rompa si el usuario escribe letras
            String entrada = teclado.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el título del libro a buscar:");
        String titulo = teclado.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("No escribiste un título.");
            return;
        }

        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = URL_BASE + tituloCodificado;

        String json = consumoAPI.obtenerDatos(url);
        DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

        List<DatosLibro> resultados = respuesta.getResults();

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }

        DatosLibro libro = resultados.get(0);

        System.out.println("----- LIBRO -----");
        System.out.println("Título: " + libro.getTitulo());

        // Autor
        if (libro.getAutores() != null && !libro.getAutores().isEmpty()) {
            System.out.println("Autor: " + libro.getAutores().get(0).getNombre());
        } else {
            System.out.println("Autor: No disponible");
        }

        // Idioma
        if (libro.getIdiomas() != null && !libro.getIdiomas().isEmpty()) {
            System.out.println("Idioma: " + libro.getIdiomas().get(0));
        } else {
            System.out.println("Idioma: No disponible");
        }

        System.out.println("Número de descargas: " + libro.getDescargas());
        System.out.println("-----------------");
    }

}
