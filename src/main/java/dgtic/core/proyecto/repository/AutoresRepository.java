package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AutoresRepository extends JpaRepository<Autores,Integer> {
}
