package org.reserva.reservashotel.reserva;

import java.time.OffsetDateTime;

public class Reserva {
    private Long id;
    private String codigo;
    /**
     *  Aqui va las FK de los ID'S huesped, habitacion y empleado
     */
    private OffsetDateTime fechaEntrada;
    private OffsetDateTime fechaSalida;
    private Integer numHuespedes;
    private String estado;
    private Integer precioTotal;
    private String observaciones;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    // Aqui igual va la parte de reserva sin solapamiento
    // Funcion que impide dos reservas activas en la misma habitacion en fechas que se cruzan
}
