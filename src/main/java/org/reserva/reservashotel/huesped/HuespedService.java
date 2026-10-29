package org.reserva.reservashotel.huesped;

import org.reserva.reservashotel.huesped.dto.HuespedCreateDTO;

/**
 * Interface en la que se agregaran las funcionalidades o logica de negocio.
 */
public interface HuespedService {

    Huesped crearHuesped(HuespedCreateDTO huespedDTO);
}
