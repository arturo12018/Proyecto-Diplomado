package dgtic.core.proyecto;

import dgtic.core.proyecto.entity.*;
import dgtic.core.proyecto.repository.*;
import dgtic.core.proyecto.service.Libro.LibroService;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.*;


import jakarta.mail.internet.MimeMessage;

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

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private CompraLibroRepository compraLibroRepository;

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

	@Test
	void likePrueba2(){


		// Cambia el tipo de retorno a Page<Libro>
		Collection<String> librosPage = librosRepository.buscarPorPatron("1");

		// Imprime los resultados
		librosPage.forEach(libro -> System.out.println(libro));
	}



}
