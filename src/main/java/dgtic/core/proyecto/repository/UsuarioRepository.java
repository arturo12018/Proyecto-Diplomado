package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
