package org.motion.motion_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.motion.motion_api.domain.entities.pitstop.Gerente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${JWT_SECRET:my-secret}")
    private String secret;

    @Value("${GOOGLE_CLIENT_ID:460171893061-335g5gggl1fk0dtg34lembspkjvr6aph.apps.googleusercontent.com}")
    private String googleClientId;

    public String generateToken(Gerente gerente) {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("motion-api")
                .withSubject(gerente.getEmail())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(algoritmo);
    }

    public String validateToken(String token) {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("motion-api")
                .build()
                .verify(token)
                .getSubject();
    }

    public String validateGoogleToken(String token) {
        try {
            RSAPublicKey publicKey = getGooglePublicKey(token);
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withAudience(googleClientId)
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return jwt.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }


    private RSAPublicKey getGooglePublicKey(String token) throws Exception {
        JwkProvider provider = new JwkProviderBuilder(new URL("https://www.googleapis.com/oauth2/v3/certs"))
                .build();

        DecodedJWT jwt = JWT.decode(token);
        String keyId = jwt.getKeyId();

        Jwk jwk = provider.get(keyId);

        if (jwk.getAlgorithm().equals("RS256") && jwk.getPublicKey() instanceof RSAPublicKey) {
            return (RSAPublicKey) jwk.getPublicKey();
        } else {
            throw new IllegalArgumentException("A chave obtida não é uma chave RSA válida");
        }
    }

}
