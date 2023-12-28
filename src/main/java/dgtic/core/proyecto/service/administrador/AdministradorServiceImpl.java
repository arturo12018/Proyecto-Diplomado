package dgtic.core.proyecto.service.administrador;


import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImpl implements AdministradorService{

    @Autowired
    AdministradorRepository administradorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<Administrador> findAll(Pageable pageable) {
        return administradorRepository.findAll(pageable);
    }

    @Override
    public void borrar(Integer id) {
        administradorRepository.deleteById(id);
    }

    @Override
    public void guardar(Administrador administrador) {
        administrador.setConstrania(
                passwordEncoder.encode(administrador.getConstrania()));
        administradorRepository.save(administrador);
    }

    @Override
    public Administrador buscarPorId(Integer id) {
        return administradorRepository.findById(id).get();
    }

    @Override
    public void modificar(Administrador administrador) {
        Administrador administradorTemp=administradorRepository.findById(administrador.getId()).get();
        administrador.setConstrania(administradorTemp.getConstrania());
        administradorRepository.save(administrador);
    }
}
