package org.reserva.reservashotel.habitacion;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.enums.EstadoHabitacion;
import org.reserva.reservashotel.enums.TipoHabitacion;
import org.reserva.reservashotel.reserva.Reserva;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "habitacion")
@Getter @Setter//Getters y Setters de los atributos.
@NoArgsConstructor//Constructor sin parametros.
@AllArgsConstructor//Constructor con todos los parametros
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Tipo de generación de ID IDENTITY
    private Long id;

    @Column(nullable = false)
    @PositiveOrZero//Solo guarda valores positivos o 0
    private Short numero;

    @Column(nullable = false)
    private Short piso;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoHabitacion tipo;

    @Column(nullable = false)
    @PositiveOrZero
    private Short capacidad;

    @Column(name = "precio_noche", nullable = false, precision = 10, scale = 2)//Como se usa NUMERIC(10,2), tenemos que hacer ese ajuste aca tambien.
    @PositiveOrZero
    private BigDecimal precioNoche;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado;
    @Column
    private String descripcion;
    @Column(nullable = false)
    private Boolean activa;

    @OneToMany(mappedBy = "habitacion", fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @PrePersist
    public void cuandoCreas(){
        this.estado = EstadoHabitacion.DISPONIBLE;
        this.activa = true;
    }
}
