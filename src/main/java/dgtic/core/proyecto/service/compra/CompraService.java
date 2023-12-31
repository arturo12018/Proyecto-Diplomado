package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompraService {
    Page<Compra> findAll(Pageable pageable);

    Compra buscarPorId(Integer id);

    Float total(Carrito carrito, List<Libro> libroList);
}
