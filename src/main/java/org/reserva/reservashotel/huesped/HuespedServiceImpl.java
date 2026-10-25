package org.reserva.reservashotel.huesped;

import org.springframework.stereotype.Service;

/**
 * Clase en la que se encontraran las funcionalidades de huéspedes
 * ya sea un CRUD o funcionalidades extra.
 */
@Service
public class HuespedServiceImpl {

    //Declaramos objetos que utilizaremos y los inyectamos en el constructor.
    private final HuespedRepository huespedRep;

    public HuespedServiceImpl(HuespedRepository huespedRep){
        this.huespedRep = huespedRep;
    }
}
