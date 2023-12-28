package dgtic.core.proyecto.service.usuario;


import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Page<Usuario> findAll(Pageable pageable);
    void borrar(Integer id);

    void guardar(Usuario usuario);

    Usuario buscarPorId(Integer id);

    void modificar(Usuario usuario);
}
