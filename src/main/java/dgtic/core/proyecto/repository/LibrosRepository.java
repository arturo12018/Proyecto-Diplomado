package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LibrosRepository extends JpaRepository<Libro,Long> {
}
