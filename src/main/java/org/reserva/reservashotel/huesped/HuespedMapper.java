package org.reserva.reservashotel.huesped;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.reserva.reservashotel.huesped.dto.HuespedCreateDTO;

/**
 * Mapper para Huesped.
 * Esta interface tiene una función util y muy necesaria, ya que al manejar datos
 * debemos controlar los datos que mostramos o regresamos en JSON por lo que en ocaciones
 * regresaremos datos contados para que exista privacidad, para esto y no tardar haciendo
 * mapeos entre Huesped(entidad) a HuespedDTO(datos simples) utilizaremos mapstructure el cual hace mapeos
 * entre listas y clases, lo unico que se necesita son dos cosas.
 * 1. Los atributos coincidan entre Entidad y DTO si no coincide entonces
 * 2. Si no coinciden los valores utilizaremos una annotation para especificar los atributos de cada cosa.
 *
 * Al realizar un mapeo te darás cuenta que sigue el concepto de sobrecarga, porque puedes utilizar el mismo nombre
 * de una función, pero el atributo al ser diferente lo vuelve una función distinta.
 */
@Mapper(componentModel = "spring")
public interface HuespedMapper {

    /**
     * Como puedes ver en esta funcion pasaremos datos de un huespedDTO a un Huesped,
     * el problema es que en huespedDTO el atributo llamado "email" y en Huesped esta como
     * "correo", por lo que no coinciden. Usando Mapping especifico cual atributo debe mapear.
     * source es para la variable en el parametro y target es para el valor de retorno de la función.
     */
    @Mapping(source = "email", target = "correo")
    Huesped paraHuesped(HuespedCreateDTO huespedDTO);

}
