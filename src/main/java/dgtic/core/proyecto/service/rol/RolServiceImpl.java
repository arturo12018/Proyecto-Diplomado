package dgtic.core.proyecto.service.rol;

import dgtic.core.proyecto.entity.Rol;
import dgtic.core.proyecto.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }
}
