package org.reserva.reservashotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    //Configuración de security para y poder acceder a todos los endpoints,
    //después se aplicará JWT y validación de roles.
    @Bean
    public SecurityFilterChain configuracion(HttpSecurity security){
        return security
                .csrf(c -> c.disable())
                .authorizeHttpRequests(http ->
                        http.requestMatchers("/**").permitAll())
                .build();
    }

    //Configuración para aplicar Hash a contraseñas con un salt de 12
    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

}
