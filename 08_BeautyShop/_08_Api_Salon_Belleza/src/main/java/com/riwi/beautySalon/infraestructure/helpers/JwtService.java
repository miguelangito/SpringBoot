package com.riwi.beautySalon.infraestructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.riwi.beautySalon.domain.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    //Crear una variable para guardar mi llave privada (FIRMA)
    private static final String SECRET_KEY = "Y2xhdmUgc3VwZXIgc2VjcmV0YSBjbGF2ZSBzdXBlciBzZWNyZXRh";


    /*Método que se va a encargar de retornar la llave de forma encriptada */
    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //Retornar nuestra llave encriptada
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /* Meétodo para construir nuestro Token */
    public String getToken(Map<String,Object> claims, User user){

        return Jwts.builder()
                .claims(claims) //Agregamos el payload del jwt
                .subject(user.getUsername()) //Agregamos de quien es el jwt
                .issuedAt(new Date(System.currentTimeMillis()))//Agregar cuando se creo el token
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //Agregamos la fecha de expiración
                .signWith(this.getKey()) //Para que el servidor Firme el token
                .compact();
    }

    /*Método  para retornar el token con los claims configurados */
    public  String getToken(User user){

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());

        return this.getToken(claims, user);
    } 

    public Claims getAllClaims(String token){
        return Jwts
                    .parser()//desarmamos el jwt
                    .verifyWith(this.getKey())//lo validamos con la fima del servidor
                    .build()//lo construimos
                    .parseSignedClaims(token)//convertir de base a json el payload
                    .getPayload();//extraemos la informacion del payload(cuerpo de jwt)
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolved){
        final Claims claims = this.getAllClaims(token);
        return claimsResolved.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return this.getClaim(token,Claims::getSubject);
    }

    public Date getExpiration(String token){
        return this.getClaim(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return this.getExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String userName = this.getUsernameFromToken(token);

        return (userName.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    
    }
}
