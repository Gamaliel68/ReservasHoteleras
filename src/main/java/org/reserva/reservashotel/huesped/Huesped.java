package org.reserva.reservashotel.huesped;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.enums.Documento;

import java.time.OffsetDateTime;

@Entity
@Table(name = "huesped")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String nombre;
    @Column(nullable = false, length = 80)
    private String apellido;
    @Column(name = "tipo_documento", nullable = false, length = 40)
    @Enumerated(EnumType.STRING)
    private Documento tipoDocumento;
    @Column(name = "numero_documento", nullable = false, length = 40)
    private String numeroDocumento;
    @Column(nullable = false, length = 40, unique = true)
    private String correo;
    @Column(nullable = false, length = 30)
    private String telefono;
    @Column(nullable = false, length = 80)
    private String nacionalidad;
    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;


    @PrePersist
    public void cuandoCreas(){
        this.creadoEn = OffsetDateTime.now();
    }


}
