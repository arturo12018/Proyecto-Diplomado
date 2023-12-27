package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador,Integer> {

    Optional<Administrador> findByCorreo(String correo);
}
