package dgtic.core.proyecto.service.idioma;

import dgtic.core.proyecto.entity.Idioma;
import dgtic.core.proyecto.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IdiomaServiceImpl implements IdiomaService{

    @Autowired
    IdiomaRepository idiomaRepository;


    @Override
    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }
}
