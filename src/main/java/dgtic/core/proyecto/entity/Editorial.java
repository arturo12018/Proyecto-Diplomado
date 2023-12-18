package dgtic.core.proyecto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Editoriales")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_editorial")
    private Integer id;

    @Column(length = 50)
    @Size(min = 3, message = "El nombre debe ser mayor a 3 caracteres")
    @NotBlank(message = "Nombre no debe ser vacío")
    private String nombre;
}
