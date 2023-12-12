package dgtic.core.proyecto.service.autores;

import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AutoresService {
    List<Autores> findAll();
    Page<Autores> findAll(Pageable pageable);
}
