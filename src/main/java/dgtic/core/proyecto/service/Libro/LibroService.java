package dgtic.core.proyecto.service.Libro;

import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroService {
    Page<Libro> findAll(Pageable pageable);
    void borrar(Long id);

    //boolean guardar(Libro libro);
    void guardar(Libro libro);

    Libro buscarPorId(Long id);

    boolean guardarLibro(Libro libro);
}
