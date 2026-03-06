package com.alura.literatura.principal;

import com.alura.literatura.model.Libro;
import com.alura.literatura.service.CatalogoService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private final Scanner teclado = new Scanner(System.in);
    private final CatalogoService catalogoService;

    public Principal(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    
                    Elija una opción:
                    1 - Buscar y registrar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    0 - Salir
                    """);

            String entrada = teclado.nextLine();

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingresa un número.");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }


    private void registrarLibroPorTitulo() {
        System.out.println("Escribe el título del libro a buscar:");
        String titulo = teclado.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("No escribiste un título.");
            return;
        }

        Optional<Libro> resultado = catalogoService.buscarYGuardarPrimerLibroPorTitulo(titulo);

        if (resultado.isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        }

        System.out.println("Guardado/Encontrado en BD:");
        System.out.println(resultado.get());
    }

    private void listarLibrosRegistrados() {
        var libros = catalogoService.listarLibros();

        if (libros.isEmpty()) {
            System.out.println("Aún no hay libros registrados.");
            return;
        }

        System.out.println("----- LIBROS REGISTRADOS -----");
        libros.forEach(System.out::println);
        System.out.println("------------------------------");
    }
    private void listarAutoresRegistrados() {
        var autores = catalogoService.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("Aún no hay autores registrados.");
            return;
        }
        System.out.println("----- AUTORES REGISTRADOS -----");
        autores.forEach(System.out::println);
        System.out.println("------------------------------");
    }
}