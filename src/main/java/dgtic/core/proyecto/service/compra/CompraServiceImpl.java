package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.repository.CompraLibroRepository;
import dgtic.core.proyecto.repository.CompraRepository;
import dgtic.core.proyecto.repository.UsuarioRepository;
import dgtic.core.proyecto.service.Libro.LibroService;
import dgtic.core.proyecto.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LibroService libroService;

    @Autowired
    CompraLibroRepository compraLibroRepository;

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



    @Override
    @Transactional
    public void guardarCompra(Compra compra,String correo,Carrito carrito) {


        //Crea Fecha
        compra.setFecha(new Date());

        //Calcula y guarda el total
        List<Libro> libroCompra=libroService.listadoLibro(carrito);
        compra.setTotal(this.total(carrito,libroCompra));

        //Busca el usuario
        compra.setUsuario(usuarioRepository.findByCorreo(correo).get());

        //Guarda compra
        compraRepository.save(compra);

        for (Libro libro : libroCompra) {
            CompraLibro compraLibro = new CompraLibro(
                    compra,
                    libro,
                    carrito.getLista().get(libro.getIsbn()),  // Valor para la cantidad
                    libro.getPrecio().doubleValue()  // Valor para el precio unitario
            );
            compraLibroRepository.save(compraLibro);
        }



    }
}
