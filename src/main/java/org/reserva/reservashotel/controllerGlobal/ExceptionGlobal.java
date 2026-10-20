package org.reserva.reservashotel.controllerGlobal;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionGlobal implements ResponseBodyAdvice<Object> {

    /**
     * La función supports funciona para validar si se procesa o no la respuesta del método
     * {@link #beforeBodyWrite, aparte esta función está acoplada para que funcione scalar
     * y poder validar ciertas acciones.
     * @param returnType the return type
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        if(StringHttpMessageConverter.class.isAssignableFrom(converterType)){ return false;}

        if(ByteArrayHttpMessageConverter.class.isAssignableFrom(converterType)){return false;}

        return true;
    }

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
    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> result = new HashMap<>();

        if(response instanceof ServletServerHttpResponse servletResponse){
            int status = servletResponse.getServletResponse().getStatus();

            if(status >= 400){
                //Si es mayor a un status code a 400 entonces regresamos un FALSEen donde
                //si recibe un status de error entonces regresamos esto.
                result.put("success", false);
                result.put("errors", body);
                return result;
            }
        }
        //Si el cuerpo de la peticion es null entonces regresamos TRUE pero regresamos un null
        //en el cuerpo de petición
        if(body == null){
            result.put("success", true);
            result.put("data", null);
        }

        //Ahora si la petición es correcta y no regresa un vacio entonces regresará un
        //TRUE y los datos del cuerpo
        result.put("success", true);
        result.put("success", body);
        return result;
    }
}
