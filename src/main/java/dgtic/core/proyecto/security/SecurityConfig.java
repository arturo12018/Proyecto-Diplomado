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
                .requestMatchers("/admin/**").hasRole("Administración general")
                .requestMatchers("/admin/inicio").hasAnyRole("Administración de usuarios y administradores", "Administración de libros")
                .requestMatchers("/admin/administrador", "/admin/usuario").hasRole("Administración de usuarios y administradores")
                .requestMatchers("/admin/libros", "/admin/autores", "/admin/editoriales").hasRole("Administración de libros")
                .requestMatchers("/").permitAll()
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
