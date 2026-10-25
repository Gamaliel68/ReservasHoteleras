package org.reserva.reservashotel.reserva;

import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl {

    private final ReservaRepository reservaRep;

    public ReservaServiceImpl(ReservaRepository reservaRep) {this.reservaRep = reservaRep;}
}
