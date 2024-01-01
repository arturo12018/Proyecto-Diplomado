package dgtic.core.proyecto.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_compra")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_usuario")
    private Usuario usuario;


    @Temporal(TemporalType.DATE)
    private Date fecha;

    private double total;

    @Column(name="Tarjeta_credito_numero",length = 16)
    @Size(min = 16,max = 16, message = "El número de la tarjeta debe ser de 16 digitos")
    @NotBlank(message = "Número de la tarjeta no debe ser vacío")
    private String tarjetaCreditoNumero;

    @Column(name = "Tarjeta_credito_cvv",length = 3)
    @Size(min = 3,max = 3, message = "El cvv de la tarjeta debe ser de 3 digitos")
    @NotBlank(message = "CVV no debe ser vacío")
    private String tarjetaCreditoCvv;

    @Column(name = "Tarjeta_credito_mes_expiracion")
    private Integer tarjetaCreditoMesExpiracion;

    @Column(name = "Tarjeta_credito_anio_expiracion")
    private Integer tarjetaCreditoAnioExpiracion;

    @Size(min = 3, message = "Dirección debe tener mas de 3 caracteres")
    @NotBlank(message = "CVV no debe ser vacío")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "ID_estado")
    private Estado Estado;

    @ManyToOne
    @JoinColumn(name = "ID_pais")
    private Pais Pais;




}
