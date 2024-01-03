package dgtic.core.proyecto.service.Libro;

import dgtic.core.proyecto.entity.Carrito;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    LibrosRepository librosRepository;

    @Override
    public Page<Libro> findAll(Pageable pageable) {
        return librosRepository.findAll(pageable);
    }

    @Override
    public void borrar(Long id) {
        librosRepository.deleteById(id);
    }

    @Override
    public void guardar(Libro libro) {
        librosRepository.save(libro);

    }



    @Override
    public Libro buscarPorId(Long id) {
        return librosRepository.findById(id).get();
    }

    @Override
    public boolean guardarLibro(Libro libro) {
        if(librosRepository.findById(libro.getIsbn()).isEmpty()){
            librosRepository.save(libro);
            return true;
        }
        return false;
    }

    @Override
    public List<Libro> busquedaPorPatron(String patron) {
        return librosRepository.findByPatron(patron);
    }

    @Override
    @Transactional(readOnly=true)
    public Page<Libro> busquedaPorTitulo(String titulo, Pageable pageable) {
        return librosRepository.findByTitulo(titulo, pageable);
    }

    @Override
    public void actualizarValoracionLibro(Long isbn, Float calificacion) {
        librosRepository.actualizarValoracionLibro(isbn,calificacion);
    }

    @Override
    public List<Libro> listadoLibro(Carrito carrito) {
        List<Libro> libroList=new ArrayList<>();
        carrito.getLista().forEach((clave, valor) -> {
            libroList.add(librosRepository.findById(clave).get());
        });

        return libroList;
    }




}
