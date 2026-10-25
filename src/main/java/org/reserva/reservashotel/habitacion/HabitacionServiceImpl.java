package org.reserva.reservashotel.habitacion;

import org.springframework.stereotype.Service;

@Service
public class HabitacionServiceImpl {

    private final HabitacionRepository habitacionRep;

    public HabitacionServiceImpl(HabitacionRepository habitacionRep) {this.habitacionRep=habitacionRep;}
}
