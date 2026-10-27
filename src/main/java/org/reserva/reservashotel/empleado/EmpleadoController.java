package org.reserva.reservashotel.empleado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
public class EmpleadoController {

    @GetMapping("/v1")
    public ResponseEntity<String> holaMundo(){
        String saludo = "Hola mundo";
        return new ResponseEntity<>(saludo, HttpStatus.ACCEPTED);
    }


}
