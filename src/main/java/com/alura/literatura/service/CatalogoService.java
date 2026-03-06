package com.alura.literatura.service;
import com.alura.literatura.dto.DatosAutor;
import com.alura.literatura.dto.DatosLibro;
import com.alura.literatura.dto.DatosRespuesta;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {
    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;
    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos conversor;

    public CatalogoService(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.consumoAPI = new ConsumoAPI();
        this.conversor = new ConvierteDatos();
    }

    public Optional<Libro> buscarYGuardarPrimerLibroPorTitulo(String titulo) {
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = URL_BASE + tituloCodificado;

        String json = consumoAPI.obtenerDatos(url);
        DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

        if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
            return Optional.empty();
        }

        DatosLibro datosLibro = respuesta.getResults().get(0);

        // Evitar duplicado por Gutenberg ID
        if (datosLibro.getId() != null && libroRepository.findByGutenbergId(datosLibro.getId()).isPresent()) {
            return libroRepository.findByGutenbergId(datosLibro.getId());
        }

        // Autor (tomamos el primero)
        DatosAutor datosAutor = (datosLibro.getAutores() != null && !datosLibro.getAutores().isEmpty())
                ? datosLibro.getAutores().get(0)
                : null;

        Autor autor = null;
        if (datosAutor != null) {
            autor = autorRepository.findByNombreIgnoreCase(datosAutor.getNombre())
                    .orElseGet(() -> autorRepository.save(
                            new Autor(datosAutor.getNombre(), datosAutor.getNacimiento(), datosAutor.getFallecimiento())
                    ));
        }

        // Idioma (tomamos el primero)
        String idioma = (datosLibro.getIdiomas() != null && !datosLibro.getIdiomas().isEmpty())
                ? datosLibro.getIdiomas().get(0)
                : null;

        Libro libro = new Libro(
                datosLibro.getId(),
                datosLibro.getTitulo(),
                idioma,
                datosLibro.getDescargas(),
                autor
        );

        return Optional.of(libroRepository.save(libro));
    }
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }
    public List<Autor> listarAutoresVivosEnAnio(Integer anio) {

        List<Autor> autores = autorRepository
                .findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(anio, anio);

        autores.addAll(
                autorRepository.findByNacimientoLessThanEqualAndFallecimientoIsNull(anio)
        );

        return autores;
    }
    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
