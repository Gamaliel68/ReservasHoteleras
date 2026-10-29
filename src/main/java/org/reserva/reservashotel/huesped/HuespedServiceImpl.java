package org.reserva.reservashotel.huesped;

import jakarta.persistence.EntityExistsException;
import org.reserva.reservashotel.exceptions.ExistsEntityException;
import org.reserva.reservashotel.huesped.dto.HuespedCreateDTO;
import org.springframework.stereotype.Service;

/**
 * Clase en la que se encontraran las funcionalidades de huéspedes
 * ya sea un CRUD o funcionalidades extra.
 */
@Service
public class HuespedServiceImpl implements HuespedService{

    //Declaramos objetos que utilizaremos y los inyectamos en el constructor.
    private final HuespedRepository huespedRep;
    private final HuespedMapper huespedMap;
    public HuespedServiceImpl(HuespedRepository huespedRep, HuespedMapper huespedMap){
        this.huespedRep = huespedRep; this.huespedMap = huespedMap;
    }

    @Override
    public Huesped crearHuesped(HuespedCreateDTO huespedDTO) {
        //Teniendo los datos del usuario, tenemos que validar ciertas cosas.
        //Buscamos al huésped mediante su correo.
        if(huespedRep.existsByCorreo(huespedDTO.getEmail())){
            //Si es true entonces mandamos una exception. // revisa ControllerGlobal y ExceptionGlobal
            throw new ExistsEntityException("Usuario existente. Ingrese un correo diferente.");
        }
        //Ahora como no hay una coincidencia con el correo toca guardar los valores.
        Huesped huesped = huespedRep.save(huespedMap.paraHuesped(huespedDTO));
        return huesped;//Regresamos huesped con sus valores, puedo hacerlo todo en el return,
        //pero para que te sea claro lo separe.


        //La funcion "save" que proviene del repository, guarda una entidad en la base de datos,
        //es como si usaramos un INSERT INTO.
        //Ahora dentro del save(), esta el mapeo de huespedDTO a Huesped.
    }

}
