package com.riwi.beautySalon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.riwi.beautySalon.domain.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

    @Autowired
    private final UserRepository userRepository;


    /**
     * Define un bean de AuthenticationManager
     * Utiliza la configuracion de spring security para obtener una configuracion ya preparada(la que viene por defecto)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
        return config.getAuthenticationManager();
    }

    /**
     * Este metodo crea y configura un DaoAuthenticationProvider, que es una de las implementaciones mas comunes para proveer
        datos a nuestra app, de esta manera guardaremos las credenciales del usuario.
     * Guardaremos toda la informacion y el tipo de cifrado que tiene su contraseña
     */

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(this.passwordEncoder());
        provider.setUserDetailsService(this.detailsService());
        
        return provider;
    }

    @Bean
    public UserDetailsService detailsService(){
        return username -> userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    /**
     * Define un bean para passwordEncoder
     * Este bean es utilizadp para encriptar y desencriptar las contraseñas en la aplicacion
     * Retorna una instancia de BCryptPasswordEncoder, es un metodo en cifrado, o hash fuertemente y ampliamente utilizado 
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
