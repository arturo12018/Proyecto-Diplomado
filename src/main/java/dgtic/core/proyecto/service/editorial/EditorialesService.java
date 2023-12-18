package dgtic.core.proyecto.service.editorial;

import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EditorialesService {
    List<Editorial> findAll();
    Page<Editorial> findAll(Pageable pageable);

    void borrar(Integer id);

    void guardar(Editorial editorial);

    Editorial buscarPorId(Integer id);
}
