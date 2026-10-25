package org.reserva.reservashotel.empleado;

import java.time.OffsetDateTime;

public class Empleado {
    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String passwordHash;
    private String rol;
    private Boolean activo;
    private OffsetDateTime fechaBaja;
    private String motivoBaja;
    // el bajaPorId hace referencia al ID del empleado
    private Long bajaPorId;
    private OffsetDateTime creadoEn;
}
