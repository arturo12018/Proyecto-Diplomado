package dgtic.core.proyecto.repository;

import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.entity.CompraLibroId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraLibroRepository extends JpaRepository<CompraLibro, CompraLibroId> {

    List<CompraLibro> findById_IdCompra(Integer integer);



}
