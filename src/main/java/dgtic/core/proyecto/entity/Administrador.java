package dgtic.core.proyecto.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Administrador implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_admin")
    private Integer id;

    @Column(length = 50)
    @Size(min = 3, message = "El nombre debe ser mayor a 3 caracteres")
    @NotBlank(message = "Nombre no debe ser vacío")
    private String nombre;

    @Column(name = "Apellido_Paterno",length = 50)
    @Size(min = 3, message = "El Apellido Paterno debe ser mayor a 3 caracteres")
    @NotBlank(message = "Apellido paterno no debe ser vacío")
    private String apellidoPaterno;


    @Column(name = "Apellido_Materno",length = 50)
    private String apellidoMaterno;

    @Email(message = "Error en el correo o ya existe")
    private String correo;

    @Column(name = "Contraseña",length = 60)
    @Size(min = 8,max = 60, message = "La contraseña debe serentre 8 y 24 caracteres")
    @NotBlank(message = "Contraseña vacia")
    private String constrania;

    @ManyToOne
    @JoinColumn(name = "Id_rol")
    private Rol rol;

    @Column(name = "Estado_Activo")
    private Boolean estadoActivo;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+this.rol.getDescripcion()));
    }

    @Override
    public String getPassword() {
        return this.constrania;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {


            return this.estadoActivo==true;



    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
