package dgtic.core.proyecto.security;

import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.entity.Usuario;
import dgtic.core.proyecto.repository.AdministradorRepository;
import dgtic.core.proyecto.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository, AdministradorRepository administradorRepository) {
        return username -> {
            Optional<Usuario> usuario = usuarioRepository.findByCorreo(username);
            if (usuario.isPresent()) {
                return usuario.get();
            }
            Optional<Administrador> administrador = administradorRepository.findByCorreo(username);
            if (administrador.isPresent()) {
                return administrador.get();
            }
            throw new UsernameNotFoundException(
                    "Usuario: " + username + " no encontrado");
        };
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/admin/inicio","/admin/sinPermisos","admin/compras/**","admin/administrador/cambiar-contrasenia").hasAnyRole("Administración_de_usuarios_y_administradores", "Administración_de_libros","Administración_general")
                .requestMatchers("/admin/administrador/**", "/admin/usuario/**").hasAnyRole("Administración_de_usuarios_y_administradores","Administración_general")
                .requestMatchers("/admin/libros/**", "/admin/autores/**", "/admin/editoriales/**").hasAnyRole("Administración_de_libros","Administración_general")
                .requestMatchers("/user/inicio-user","/user/compras/**","/user/cambiar-contrasenia").hasRole("USER")
                .requestMatchers("/**","/autenticacion").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/admin/sinPermisos")
                .and()
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/autenticacion")
                                .usernameParameter("usuario")
                                .passwordParameter("pwd")
                                .defaultSuccessUrl("/tipoUsuario", false));
        return http.build();
    }


}
