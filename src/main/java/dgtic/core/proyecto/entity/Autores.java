package dgtic.core.proyecto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_autor")
    private String id;

    @Column(name = "Apellido_Materno",length = 50)
    private String apellidoMaterno;

    @Column(name = "Apellido_Paterno",length = 50)
    @Size(min = 3, message = "El Apellido Paterno debe ser mayor a 3 caracteres")
    @NotBlank(message = "Apellido paterno no debe ser vacío")
    private String apellidoPaterno;

    @Column(length = 50)
    @Size(min = 3, message = "El nombre debe ser mayor a 3 caracteres")
    @NotBlank(message = "Nombre no debe ser vacío")
    private String nombre;

    @ManyToMany(mappedBy = "autores",fetch = FetchType.EAGER)
    private List<Libro> libros;

    @Override
    public String toString() {
        return "Autores{" +
                "id='" + id + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
