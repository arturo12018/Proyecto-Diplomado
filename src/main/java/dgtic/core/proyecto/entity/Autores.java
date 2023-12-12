package dgtic.core.proyecto.entity;

import jakarta.persistence.*;
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

    @Column(name = "Apellido_Materno")
    private String apellidoMaterno;

    @Column(name = "Apellido_Paterno")
    private String apellidoPaterno;

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
