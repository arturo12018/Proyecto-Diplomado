package dgtic.core.proyecto.repository;

import dgtic.core.proyecto.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado,Integer> {

    List<Estado> findByPais_Id(Integer id);

}
