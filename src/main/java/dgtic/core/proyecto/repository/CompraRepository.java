package dgtic.core.proyecto.repository;


import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.CompraLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra,Integer> {
    Page<Compra> findByUsuario_Id(Integer id, Pageable pageable);
}
