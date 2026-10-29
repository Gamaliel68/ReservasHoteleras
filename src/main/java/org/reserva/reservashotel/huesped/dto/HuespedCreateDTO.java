package org.reserva.reservashotel.huesped.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.reserva.reservashotel.enums.Documento;

/**
 * DTO para pasar los datos básicos del huesped
 */
//Las 4 annotations son para no escribir codigo, ya que estas generan,
//los getters y setters de los atributos de la clase.
// Y un constructor vacío y otro con todos los atributos.

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class HuespedCreateDTO {
    @NotBlank(message = "Ingrese un nombre.")
    private String nombre;
    @NotBlank(message = "Ingrese un apellido.")
    private String apellido;
    @NotNull(message = "Ingrese un documento.")
    private Documento tipoDocumento;
    @NotBlank(message = "Ingrese un numero de documento.")
    private String numeroDocumento;
    @Email(message = "Ingrese un correo valido.")
    private String email;
    private String telefono;
    private String nacionalidad;

}
