package org.reserva.reservashotel.ticket;

import jakarta.persistence.*;
import org.reserva.reservashotel.enums.EstadoPago;
import org.reserva.reservashotel.enums.MetodoPago;

import java.time.OffsetDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // En el numero hay una secuencia consecutiva (T-000001,...)
    @Column(nullable = false, unique = true)
    private String numero;
    /**
     *  Aqui van las FK de los ID'S de reserva y empleado
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private String monto;
    @Column(name = "metodo_pago" ,nullable = false)
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPago estado;
    @Column(name = "emitido_en", nullable = false)
    private OffsetDateTime emitidoEn;

    @PrePersist
    public void cuandoCreas(){
        this.emitidoEn = OffsetDateTime.now();
    }

}
