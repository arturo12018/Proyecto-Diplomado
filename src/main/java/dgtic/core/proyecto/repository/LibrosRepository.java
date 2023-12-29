package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro,Long> {

    @Query("select p from Libro p where p.titulo like %?1%")
    Page<Libro> findByTitulo(String dato, Pageable pageable);

    @Query("select p.titulo from Libro p where p.titulo like %:dato%")
    List<String> buscarPorPatron(String dato);



}
