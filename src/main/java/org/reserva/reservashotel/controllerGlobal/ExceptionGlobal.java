package org.reserva.reservashotel.controllerGlobal;

import org.reserva.reservashotel.exceptions.ExistsEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que atrapara todas las exceptions, aquí podemos mostrar un mensaje o
 * regresar un valor de status especifico.
 * Para no escribir try{}catch en todos los service y componentes, lo meteremos en un controlador que revisara
 * cada salida y entrada, asi no escribimos mucho código y ahorramos líneas.
 */
//Cuando se entienda mejor agregar -> implements ResponseBodyAdvice<Object>
@RestControllerAdvice
public class ExceptionGlobal  {

    @ExceptionHandler(ExistsEntityException.class)
    public ResponseEntity<Map<String, String>> entidadExistente(
            ExistsEntityException error
    ){
        Map<String, String> mapa = new HashMap<>();
        mapa.put("Error: ", error.getMessage());
        return new ResponseEntity<>(mapa, HttpStatus.CONFLICT);
    }





//En un futuro utilizaremos otro tipo de regreso de datos, pero ahora entenderemos desde cero.

    /**
     * La función supports funciona para validar si se procesa o no la respuesta del método
     * {@link #beforeBodyWrite, aparte esta función está acoplada para que funcione scalar
     * y poder validar ciertas acciones.
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return
     */
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//
//        if(StringHttpMessageConverter.class.isAssignableFrom(converterType)){ return false;}
//
//        if(ByteArrayHttpMessageConverter.class.isAssignableFrom(converterType)){return false;}
//
//        return true;
//    }

    /**
     * Esta función sirve para encubrir el cuerpo de la petición con datos extra, en este caso
     * regresará una acción y el resultado.
     * @param body the body to be written
     * @param returnType the return type of the controller method
     * @param selectedContentType the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request the current request
     * @param response the current response
     * @return
     */
//    @Override
//    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)
//    {
//        Map<String, Object> result = new HashMap<>();
//
//        if(response instanceof ServletServerHttpResponse servletResponse){
//            int status = servletResponse.getServletResponse().getStatus();
//
//            if(status >= 400){
//                //Si es mayor a un status code a 400 entonces regresamos un FALSEen donde
//                //si recibe un status de error entonces regresamos esto.
//                result.put("success", false);
//                result.put("errors", body);
//                return result;
//            }
//        }
//        //Si el cuerpo de la peticion es null entonces regresamos TRUE pero regresamos un null
//        //en el cuerpo de petición
//        if(body == null){
//            result.put("success", true);
//            result.put("data", null);
//        }
//
//        //Ahora si la petición es correcta y no regresa un vacio entonces regresará un
//        //TRUE y los datos del cuerpo
//        result.put("success", true);
//        result.put("success", body);
//        return result;
//    }


    /**
     * Este es la función que detecta los errores para EntidadExistenteException o EntityExistException.
     * @param error
     * @return
     */
//    @ExceptionHandler(EntityExistException.class)
//    public ResponseEntity<Object> entidadExistente(
//            EntityExistException error
//    ){
//        Map<String, String> mapa = new HashMap<>();
//        mapa.put("Error: ", error.getMessage());
//        return new ResponseEntity<>(mapa, HttpStatus.CONFLICT);
//    }






}
