package org.reserva.reservashotel.ticket;

import java.time.OffsetDateTime;

public class Ticket {
    private Long id;
    // En el numero hay una secuencia consecutiva (T-000001,...)
    private String numero;
    /**
     *  Aqui van las FK de los ID'S de reserva y empleado
     */
    private String monto;
    private String metodoPago;
    private String estado;
    private OffsetDateTime emitidoEn;
}
