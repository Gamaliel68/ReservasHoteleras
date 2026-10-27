package org.reserva.reservashotel.reserva;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.empleado.Empleado;
import org.reserva.reservashotel.enums.EstadoReserva;
import org.reserva.reservashotel.habitacion.Habitacion;
import org.reserva.reservashotel.huesped.Huesped;
import org.reserva.reservashotel.ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;
    @Column(name = "num_huespedes", nullable = false)
    @Positive
    private Short numHuespedes; //Realiza una prueba cambiando Short por Integer y ejecutalo. Busca el error.
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;
    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;
    @Column//Sin ningun parametro de entrada.
    private String observaciones;
    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;
    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;
/*
 *  Aqui va las FK de los ID'S huesped, habitacion y empleado
 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huesped_id", nullable = false)
    private Huesped huesped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @OneToMany(mappedBy = "reserva")
    private List<Ticket> tickets;
    // Aqui igual va la parte de reserva sin solapamiento
    // Funcion que impide dos reservas activas en la misma habitacion en fechas que se cruzan

    @PrePersist
    public void cuandoCreas(){
        this.creadoEn = OffsetDateTime.now();
        this.actualizadoEn = OffsetDateTime.now();
    }

    //Cuando se realiza una actualizacion entonces se realizara el cambio de fecha a la hora en la que se
    //actualizo.
    @PreUpdate
    public void cuandoActualice(){
        this.actualizadoEn = OffsetDateTime.now();
    }

}
