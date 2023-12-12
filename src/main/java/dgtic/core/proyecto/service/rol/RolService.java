package dgtic.core.proyecto.service.rol;


import dgtic.core.proyecto.entity.Rol;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RolService {
    List<Rol> findAll();

}
