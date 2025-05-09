package com.example.UserAuthenticationSpring.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token) {
        try {
            String username = extractUsername(token); // Extract username from the token
            return validateToken(token, username); // Validate token using the extracted username
        } catch (Exception e) {
            return false; // If any error occurs, treat the token as invalid
        }
    }

    public Set<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?>) {
            List<?> rolesList = (List<?>) rolesObject;
            return rolesList.stream()
                    .filter(role -> role instanceof String)
                    .map(role -> (String) role)
                    .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }



}



















//package com.example.UserAuthenticationSpring.security;
//
//import com.example.UserAuthenticationSpring.model.Role;
//import com.example.UserAuthenticationSpring.model.User;
//import com.example.UserAuthenticationSpring.repository.UserRepository;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtUtil {
//
//    //secret key
//    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//    //validation time or expiration time
//    private final int jwtExpirationMs = 172800000;
//
//    private final UserRepository userRepository;
//
//    public JwtUtil(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    //token generation
//    public String generateToken(String userName) {
//        Optional<User> user = userRepository.findByUserName(userName);
//        Set<Role> roles = user.get().getRoles();
//
//        //add roles to the token
//        return Jwts.builder()
//                .setSubject(userName)
//                .claim("roles", roles.stream()
//                        .map(role -> role.getName())
//                        .collect(Collectors.joining(",")))
//                .setIssuedAt(new Date()) // Corrected: Use new Date()
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)) // Corrected: Use System.currentTimeMillis()
//                .signWith(secretKey)
//                .compact();
//    }
//
//    //extract username
//    public String extractUserName(String token) {
//        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().getSubject();
//    }
//
//    //extracting roles
//    public Set<String> extractRoles(String token) {
//        String rolesString = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).getBody().get("roles", String.class);
//        return Set.of(rolesString);
//    }
//
//    //token validation
//    public boolean isTokenValid(String token){
//        try{
//            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token);
//            return true;
//        }catch (JwtException | IllegalArgumentException e)
//        {
//            return false;
//        }
//
//    }
//}
