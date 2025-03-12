package br.com.SmallManager.infra.security;

import br.com.SmallManager.domain.SystemUser;
import br.com.SmallManager.infra.ParameterProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Slf4j
@Service
public class TokenService {

    private final ParameterProperties parameterProperties;

    public TokenService(ParameterProperties parameterProperties) {
        this.parameterProperties = parameterProperties;
    }

    public String generateToken(SystemUser systemUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(parameterProperties.getTokenApplication());
            return JWT.create()
                    .withIssuer("SmallManager")
                    .withSubject(systemUser.getName())
                    .withExpiresAt(expirationTimes(parameterProperties.getExpireTokenTime()))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            log.error(exception.getMessage());
            return "Token invalido!";
        }
    }

    public Optional<String> verifyToken(String token){
        DecodedJWT decodedJWT;

        Algorithm algorithm =  Algorithm.HMAC256(parameterProperties.getTokenApplication());
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("SmallManager")
                .build();

        decodedJWT = verifier.verify(token);
        return Optional.of(decodedJWT.getSubject());

    }

    public String generateRefreshToken(SystemUser systemUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(parameterProperties.getTokenApplication());
            return JWT.create()
                    .withIssuer("SmallManager")
                    .withSubject(systemUser.getId().toString())
                    .withExpiresAt(expirationTimes(parameterProperties.getExpireRefreshtokenTime()))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            log.error(exception.getMessage());
            return "Token invalido!";
        }
    }

    private Instant expirationTimes(long times){
        return LocalDateTime.now().plusMinutes(times).toInstant(ZoneOffset.of("-03:00"));
    }

}
