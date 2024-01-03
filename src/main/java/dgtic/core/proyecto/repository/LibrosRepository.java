package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Libro;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro,Long> {

    @Query("select p from Libro p where p.titulo like %?1%")
    Page<Libro> findByTitulo(String dato, Pageable pageable);

    @Query("select p from Libro p where p.titulo like %?1%")
    List<Libro> findByPatron(String dato);

    @Transactional
    @Modifying
    @Query("UPDATE Libro l SET l.valoracion = :nuevaValoracion WHERE l.isbn = :libroId")
    void actualizarValoracionLibro(Long libroId, Float nuevaValoracion);



}
