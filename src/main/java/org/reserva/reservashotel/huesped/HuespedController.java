package org.reserva.reservashotel.huesped;

import jakarta.validation.Valid;
import org.reserva.reservashotel.huesped.dto.HuespedCreateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/guest")
@Validated
public class HuespedController {

    private final HuespedServiceImpl huespedServ;

    public HuespedController(HuespedServiceImpl huespedServ){
        this.huespedServ = huespedServ;
    }

    @PostMapping
    public ResponseEntity<Huesped> crearHuesped(
            @RequestBody @Valid HuespedCreateDTO huespedDTO
            ){
        Huesped huesped = huespedServ.crearHuesped(huespedDTO);
        return new ResponseEntity<>(huesped, HttpStatus.CREATED);
    }


    /**
     * Existen diferentes metodos HTTP y cada uno cumple con una función,
     * si el usuario quiere algo del servidor entonces es un metodo GET.
     * Si el usuario quiere crear algo en el servidor entonces usamos el metodo POST.
     * Si el usuario quiere actualizar algo existente en el servidor usamos UPDATE(actualizar gran cantidad de datos.)
     * Si el usuario quiere hacer una actualización minima en el servidor usamos PATCH.
     * Si el usuario quiere eliminar algo del servidor usamos DELETE. Usualmente no eliminaremos por todas las relaciones.
     * Hay muchos metodos mas pero estos son los mas comunes y utilizados. Investiga mas de esto.
     * Revisa el curso por que es necesario que sepas que es un @RequestBody, @pathVariable o @RequestParam.
     * El uso de @Valid y @Validate. por que usamos @RestController y no @Controller
     */


}
