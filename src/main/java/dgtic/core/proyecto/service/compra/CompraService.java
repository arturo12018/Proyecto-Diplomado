package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.Editorial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompraService {
    Page<Compra> findAll(Pageable pageable);

    Compra buscarPorId(Integer id);
}
