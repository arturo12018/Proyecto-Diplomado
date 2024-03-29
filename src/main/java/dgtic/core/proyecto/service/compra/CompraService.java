package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    Page<Compra> findAll(Pageable pageable);

    Compra buscarPorId(Integer id);

    Float total(Carrito carrito, List<Libro> libroList);

    Page<Compra> listadosComprasPorID(Integer integer, Pageable pageable);


    void guardarCompra(Compra compra,String correo,Carrito carrito);
}
