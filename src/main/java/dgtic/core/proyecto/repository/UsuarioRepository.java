package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {


    Optional<Usuario> findByCorreo(String correo);


}
