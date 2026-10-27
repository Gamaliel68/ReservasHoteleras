package org.reserva.reservashotel.empleado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.enums.RolEmpleado;
import org.reserva.reservashotel.reserva.Reserva;
import org.reserva.reservashotel.ticket.Ticket;

import java.time.OffsetDateTime;
import java.util.List;

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

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "empleado")
    private List<Ticket> tickets;

    //Empleado que da de baja.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baja_por_id")
    private Empleado dadoDeBajaPor;

    //Lista de empleados que fueron dados de baja.
    @OneToMany(mappedBy = "dadoDeBajaPor", fetch = FetchType.LAZY)
    private List<Empleado> empleadosDadosDeBaja;

    //Me falta la relacion a:n entre empleado y empleado, como se hace esa jajaja


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
