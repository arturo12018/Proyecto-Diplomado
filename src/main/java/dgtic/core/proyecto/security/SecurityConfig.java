package dgtic.core.proyecto.security;

import dgtic.core.proyecto.entity.Administrador;
import dgtic.core.proyecto.repository.AdministradorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
    public UserDetailsService userDetailsService(AdministradorRepository administradorRepository) {
        return username -> {
            Optional<Administrador> administrador = administradorRepository.findByCorreo(username);
            if (administrador.isPresent()) {
                return administrador.get();
            }
            throw new UsernameNotFoundException(
                    "Administrador: " + username + " no encontrado");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .requestMatchers("/admin/inicio","/admin/sinPermisos").hasAnyRole("Administración_de_usuarios_y_administradores", "Administración_de_libros","Administración_general")
                .requestMatchers("/admin/administrador/**", "/admin/usuario/**").hasAnyRole("Administración_de_usuarios_y_administradores","Administración_general")
                .requestMatchers("/admin/libros/**", "/admin/autores/**", "/admin/editoriales/**").hasAnyRole("Administración_de_libros","Administración_general")
                //.requestMatchers("/admin/**").hasRole("Administración_general")
                .requestMatchers("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/admin/sinPermisos")
                .and()
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/adminlogin")
                                .loginProcessingUrl("/autenticacion")
                                .usernameParameter("usuario")
                                .passwordParameter("pwd")
                                .defaultSuccessUrl("/admin/inicio", true));
        return http.build();
    }

}
