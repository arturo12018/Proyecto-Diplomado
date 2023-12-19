package dgtic.core.proyecto;

import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.entity.CompraLibroId;
import dgtic.core.proyecto.repository.AdministradorRepository;
import dgtic.core.proyecto.repository.CompraLibroRepository;
import dgtic.core.proyecto.repository.CompraRepository;
import dgtic.core.proyecto.repository.LibrosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	CompraLibroRepository administradorRepository;

	@Test
	void contextLoads() {

		//administradorRepository.findAll().forEach(System.out::println);


		List<CompraLibro> compraLibros=administradorRepository.findById_IdCompra(1);
		compraLibros.forEach(System.out::println);

	}

}
