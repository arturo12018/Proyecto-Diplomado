package dgtic.core.proyecto.service.autores;

import dgtic.core.proyecto.entity.Autores;
import dgtic.core.proyecto.entity.Libro;
import dgtic.core.proyecto.repository.AutoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresServiceImpl implements AutoresService{

    @Autowired
    AutoresRepository autoresRepository;

    @Override
    public List<Autores> findAll() {
        return autoresRepository.findAll();
    }

    @Override
    public Page<Autores> findAll(Pageable pageable) {
        return autoresRepository.findAll(pageable);
    }

}
