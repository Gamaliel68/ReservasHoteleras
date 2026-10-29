package org.reserva.reservashotel.huesped;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Si no agregamos repository spring no reconocerá la clase y no ejecutará.
@Repository
//Los valores detro de <Huésped, Long>, el primero es la entidad a la que haces referencia, la segunda es la PK de la tabla,
//puede ser Long o UUID, o algún identificador.
public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    /**
     * Función que buscara en la tabla huéspedes un correo, si lo encuentra
     * regresará un True, si no un false
     * @param correo : Correo del Huésped nuevo.
     * @return : Si encuentra true, si no false.
     */
    boolean existsByCorreo(String correo);

    /* JPA te permite mapear entidades, como lo hicimos antes, y en combinación con Hibernate
    * permite hacer consultas sin necesidad de usar SQL o postgresql para consultas, ya que Hibernate
    * incluye funciones que realizan consultas, estas te permiten buscar por filtros y relacionando tablas,
    * pero en esta última se quedan cortas por lo que utilizaremos estas funciones en cosas simples, si
    * necesitamos consultas que incluyan relaciones utilizaremos consultas nativas o de postgresql */
}
