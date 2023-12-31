package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    CompraRepository compraRepository;

    @Override
    public Page<Compra> findAll(Pageable pageable) {
        return compraRepository.findAll(pageable);
    }

    @Override
    public Compra buscarPorId(Integer id) {
        return compraRepository.findById(id).get();
    }

    @Override
    public Float total(Carrito carrito, List<Libro> libroList) {
        Float total=0.0F;

        for(Libro libro:libroList){
            total+=libro.getPrecio()*carrito.getLista().get(libro.getIsbn());

        };
        return total;
    }

    @Override
    public Page<Compra> listadosComprasPorID(Integer id, Pageable pageable) {
        return compraRepository.findByUsuario_Id(id,pageable);
    }
}
