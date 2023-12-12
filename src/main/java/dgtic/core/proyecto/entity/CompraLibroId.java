package dgtic.core.proyecto.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CompraLibroId implements Serializable {

    @Column(name = "ID_compra")
    private int idCompra;

    @Column(name = "ISBN")
    private Long isbn;

    @Override
    public String toString() {
        return "Id{" +
                "idCompra=" + idCompra +
                ", isbn=" + isbn +
                '}';
    }


}
