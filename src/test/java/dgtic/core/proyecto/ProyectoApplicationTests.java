package dgtic.core.proyecto;

import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.*;
import dgtic.core.proyecto.service.Libro.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class ProyectoApplicationTests {

	@Autowired
	AdministradorRepository administradorRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	LibroService libroService;

	@Autowired
	LibrosRepository librosRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

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

	@Test
	void creaciuonUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNombre("Juan");
		usuario.setApellidoPaterno("Pérez");
		usuario.setApellidoMaterno("López");
		usuario.setCorreo("usuario@ejemplo.com");
		usuario.setConstrasena(
				passwordEncoder.encode("usuarios"));
		usuario.setEstadoActivo(true);

		usuarioRepository.save(usuario);
	}


	@Test
	void likePrueba(){
		// Suponiendo que Pageable es una interfaz y estás usando PageRequest para crearlo
		Pageable pageable = PageRequest.of(0, 10);

		// Cambia el tipo de retorno a Page<Libro>
		Page<Libro> librosPage = librosRepository.findByTitulo("1", pageable);

		// Imprime los resultados
		librosPage.forEach(libro -> System.out.println(libro));
	}

}
