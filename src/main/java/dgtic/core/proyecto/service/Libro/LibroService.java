package dgtic.core.proyecto.service.Libro;

import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LibroService {
    Page<Libro> findAll(Pageable pageable);
    void borrar(Long id);


    void guardar(Libro libro);

    Libro buscarPorId(Long id);

    boolean guardarLibro(Libro libro);

    List<Libro> busquedaPorPatron(String patron);

    Page<Libro> busquedaPorTitulo(String titulo,Pageable pageable);
}
