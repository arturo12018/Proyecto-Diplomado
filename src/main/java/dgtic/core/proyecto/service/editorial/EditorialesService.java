package dgtic.core.proyecto.service.editorial;

import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Editorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EditorialesService {
    List<Editorial> findAll();
    Page<Editorial> findAll(Pageable pageable);
}
