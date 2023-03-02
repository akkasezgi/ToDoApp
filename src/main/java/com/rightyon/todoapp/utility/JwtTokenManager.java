package com.rightyon.todoapp.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {

    private final String keyValue = "etbpcQpLkNsUmYCYKwDmp2iwQVxbYAbUKUuK3Tdv7C4KNQtuL9";

    public Optional<String> createToken(Long id){
        String token;

        Long exDate = 1000L*60*15;
        try{

            token =  JWT.create()
                    .withAudience()
                    .withClaim("id",id)
                    .withClaim("howtopage","UserService")
                    .withClaim("isOne",true)
                    .withIssuer("rightyon")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                    .sign(Algorithm.HMAC512(keyValue));
            return Optional.of(token);
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    public Boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(keyValue);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("rightyon")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null)
                return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }


    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(keyValue);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("rightyon")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT==null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch (Exception exception){
            return Optional.empty();
        }

    }
}
