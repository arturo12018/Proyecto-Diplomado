package dgtic.core.proyecto.entity;


import jakarta.persistence.*;
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

    @Column(name="Tarjeta_credito_numero")
    private String tarjetaCreditoNumero;

    @Column(name = "Tarjeta_credito_cvv")
    private String tarjetaCreditoCvv;

    @Column(name = "Tarjeta_credito_mes_expiracion")
    private Integer tarjetaCreditoMesExpiracion;

    @Column(name = "Tarjeta_credito_anio_expiracion")
    private Integer tarjetaCreditoAnioExpiracion;

    private String direccion;

    @ManyToOne
    @JoinColumn(name = "ID_estado")
    private Estado Estado;

    @ManyToOne
    @JoinColumn(name = "ID_pais")
    private Pais Pais;




}
