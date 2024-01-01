package dgtic.core.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "compra_libro")
public class CompraLibro {

    @EmbeddedId
    private CompraLibroId id;

    @ManyToOne
    @MapsId("idCompra") // Use the attribute name from CompraLibroId
    @JoinColumn(name = "ID_compra")
    private Compra compra;

    @ManyToOne
    @MapsId("isbn") // Use the attribute name from CompraLibroId
    @JoinColumn(name = "ISBN")
    private Libro libro;


    private int cantidad;

    @Column(name = "Precio_unitario")
    private Double precioUnitario;

    public CompraLibro(Compra compra, Libro libro, int cantidad, Double precioUnitario) {
        this.id=new CompraLibroId(compra.getId(), libro.getIsbn());
        this.compra = compra;
        this.libro = libro;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;

    }
}
