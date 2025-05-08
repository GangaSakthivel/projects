package com.example.UserAuthenticationSpring.security;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    //secret key
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //validation time or expiration time
}
