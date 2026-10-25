package org.reserva.reservashotel.empleado;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl {

    private final EmpleadoRepository empleadoRep;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRep) {this.empleadoRep =  empleadoRep;}
}
