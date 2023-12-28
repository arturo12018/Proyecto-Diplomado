package dgtic.core.proyecto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_usuario")
    private Integer id;


    @Column(length = 50)
    @Size(min = 3, message = "Nombre debe ser mayor a 3 caracteres")
    @NotBlank(message = "Nombre no debe ser vacío")
    private String nombre;


    @Column(name = "Apellido_Paterno",length = 50)
    @Size(min = 3, message = "El Apellido Paterno debe ser mayor a 3 caracteres")
    @NotBlank(message = "Apellido paterno no debe ser vacío")
    private String apellidoPaterno;

    @Column(name = "Apellido_Materno")
    private String apellidoMaterno;

    @Email(message = "Error en el correo o ya existe")
    private String correo;


    @Column(name = "Contraseña",length = 60)
    @Size(min = 8,max = 60, message = "La contraseña debe serentre 8 y 24 caracteres")
    @NotBlank(message = "Contraseña vacia")
    private String constrasena;

    @Column(name = "Estado_Activo")
    private boolean estadoActivo;


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", correo='" + correo + '\'' +
                ", constrasena='" + constrasena + '\'' +
                ", estadoActivo=" + estadoActivo +
                '}';
    }
}
