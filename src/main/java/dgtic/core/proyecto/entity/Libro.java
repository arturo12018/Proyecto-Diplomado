package dgtic.core.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dgtic.core.proyecto.validation.LongDe13Digitos;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @LongDe13Digitos(message = "Error con isbn debe ser de 13 digitos")
    @Column(unique = true)
    private Long isbn;

    @Size(min = 1, message = "El titulo debe ser mayor a 1 caracteres")
    @NotBlank(message = "Titulo no debe ser vacío")
    private String titulo;

    @Column(length = 250)
    @Size(max = 250)
    @Size(min = 10, message = "El Descripcion debe ser mayor a 10 caracteres")
    @NotBlank(message = "Error descripcion esta vacio")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ID_editorial")
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "ID_idioma")
    private Idioma idioma;

    @Column(name = "Anio_publicacion")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anioPublicacion;

    //@NotBlank(message = "Error eicion esta vacio")
    private Integer edicion;

    @Column(name = "Imagen_portada")
    private String imagenPortada;


    private Float valoracion;

    //@NotBlank(message = "Error precio esta vacio")
    private Float precio;

    @Column(name = "Numero_paginas")
   // @NotBlank(message = "Error número de paginas esta vacio")
    private Integer numeroPaginas;




   @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autor_libro",
            joinColumns = @JoinColumn(name = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "ID_autor")
    )
    private List<Autores> autores;

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", editorial=" + editorial +
                ", idioma=" + idioma +
                ", anioPublicacion=" + anioPublicacion +
                ", edicion=" + edicion +
                ", imagenPortada='" + imagenPortada + '\'' +
                ", valoracion=" + valoracion +
                ", precio=" + precio +
                ", numeroPaginas=" + numeroPaginas +
                '}';
    }
}
