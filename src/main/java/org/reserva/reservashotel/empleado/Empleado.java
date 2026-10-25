package org.reserva.reservashotel.empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.enums.RolEmpleado;

import java.time.OffsetDateTime;

@Entity
@Table(name = "empleado")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column(nullable = false, length = 80)
    private String apellido;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String passwordHash;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolEmpleado rol;
    @Column(nullable = false)
    private Boolean activo;
    @Column(name = "fecha_baja")
    private OffsetDateTime fechaBaja;
    @Column(name = "motivo_baja")
    private String motivoBaja;
    // el bajaPorId hace referencia al ID del empleado
    private Long bajaPorId;
    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;


    /**
     * Cuando se tienen columnas que necesitan un valor DEFAULT,
     * meteremos los datos antes del insert, el PrePersist,
     * se puede usar como DEFAULT.
     */
    @PrePersist
    public void cuandoCreas(){
        this.activo = true;
        this.creadoEn = OffsetDateTime.now();
    }



}
