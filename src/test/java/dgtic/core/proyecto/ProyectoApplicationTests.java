package dgtic.core.proyecto;

import dgtic.core.proyecto.repository.AdministradorRepository;
import dgtic.core.proyecto.repository.CompraRepository;
import dgtic.core.proyecto.repository.LibrosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	CompraRepository administradorRepository;

	@Test
	void contextLoads() {
		administradorRepository.findAll().forEach(System.out::println);
	}

}
