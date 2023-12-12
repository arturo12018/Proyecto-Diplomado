package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends JpaRepository<Rol,Integer> {
}
