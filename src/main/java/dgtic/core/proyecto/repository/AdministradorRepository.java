package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdministradorRepository extends JpaRepository<Administrador,Integer> {
}
