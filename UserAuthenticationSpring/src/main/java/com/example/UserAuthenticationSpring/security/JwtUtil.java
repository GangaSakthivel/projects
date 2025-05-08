package com.example.UserAuthenticationSpring.security;

import com.example.UserAuthenticationSpring.model.Role;
import com.example.UserAuthenticationSpring.model.User;
import com.example.UserAuthenticationSpring.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    //secret key
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    //validation time or expiration time
    private final int jwtExpirationMs = 172800000;

    private final UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //token generation
    public String generateToken(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        Set<Role> roles = user.get().getRoles();

        //add roles to the token
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", roles.stream()
                        .map(role -> role.getName())
                        .collect(Collectors.joining(",")))
                .setIssuedAt(new Date()) // Corrected: Use new Date()
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Corrected: Use System.currentTimeMillis()
                .signWith(secretKey)
                .compact();
    }

    //extract username
    public String extractUserName(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().getSubject();
    }

    //extracting roles
    public Set<String> extractRoles(String token) {
        String rolesString = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().get("roles", String.class);
        return Set.of(rolesString);
    }

    //token validation
    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token);
            return true;
        }catch (JwtException | IllegalArgumentException e)
        {
            return false;
        }

    }
}
