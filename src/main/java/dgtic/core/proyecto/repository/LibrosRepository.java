package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro,Long> {

    @Query("select p from Libro p where p.titulo like %?1%")
    Page<Libro> findByTitulo(String dato, Pageable pageable);

    @Query("select p from Libro p where p.titulo like %?1%")
    List<Libro> buscarPorPatron(String dato);

}
