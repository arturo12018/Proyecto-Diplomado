package dgtic.core.proyecto.service.administrador;

import dgtic.core.proyecto.entity.Administrador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdministradorService {

    Page<Administrador> findAll(Pageable pageable);
    void borrar(Integer id);

    void guardar(Administrador administrador);

    Administrador buscarPorId(Integer id);


    void modificar(Administrador administrador);

}
