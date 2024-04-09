package org.motion.motion_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.AlgorithmParameterGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${JWT_SECRET:my-secret}")
    private String secret;

    public String generateToken(Gerente gerente) {

        var algoritmo = Algorithm.HMAC256(secret);
        String token = JWT
                .create()
                .withIssuer("motion-api")
                .withSubject(gerente.getEmail())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                //.withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC)))
                .sign(algoritmo);
        return token;


    }

    public String validateToken(String token){
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("motion-api")
                .build()
                .verify(token)
                .getSubject();
    }

}
