package dgtic.core.proyecto.service.compra;

import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl implements CompraService{

    @Autowired
    CompraRepository compraRepository;

    @Override
    public Page<Compra> findAll(Pageable pageable) {
        return compraRepository.findAll(pageable);
    }
}
