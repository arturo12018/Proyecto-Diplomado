package dgtic.core.proyecto.service.editorial;

import dgtic.core.proyecto.entity.Editorial;
import dgtic.core.proyecto.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialesServiceImpl implements EditorialesService{

    @Autowired
    EditorialRepository editorialRepository;

    @Override
    public List<Editorial> findAll() {
        return editorialRepository.findAll();
    }

    @Override
    public Page<Editorial> findAll(Pageable pageable) {
        return editorialRepository.findAll(pageable);
    }
}
