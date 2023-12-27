package dgtic.core.proyecto;

import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.entity.CompraLibroId;
import dgtic.core.proyecto.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	AdministradorRepository administradorRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {



		Administrador admin = new Administrador();
		admin.setNombre("Juan");
		admin.setApellidoPaterno("Pérez");
		admin.setApellidoMaterno("López");
			admin.setCorreo("juan.perez2@ejemplo.com");
		admin.setConstrania(
				passwordEncoder.encode("administracion"));
		admin.setRol(rolRepository.findById(1).get());
		admin.setEstadoActivo(true);

		administradorRepository.save(admin);



	}

}
