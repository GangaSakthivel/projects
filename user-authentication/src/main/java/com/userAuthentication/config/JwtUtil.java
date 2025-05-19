package com.userAuthentication.security;

import com.userAuthentication.model.Role;
import com.userAuthentication.model.User;
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

    // Extract phone number (subject) from JWT token
    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract any claim using a resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parse and get all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Generate JWT token for a given user
    public String generateToken(User user) {
        // Extract role names from roles enum RoleName as String (e.g. "USER", "ADMIN")
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)              // get enum RoleName
                .map(Enum::name)                     // convert enum to String
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(user.getPhoneNumber())   // phone number as subject
                .claim("roles", roleNames)            // custom claim for roles
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token against phone number and expiry
    public boolean validateToken(String token, String phoneNumber) {
        final String extractedPhoneNumber = extractPhoneNumber(token);
        return extractedPhoneNumber.equals(phoneNumber) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Check if token is valid by verifying phone number and expiration
    public boolean isTokenValid(String token) {
        try {
            String phoneNumber = extractPhoneNumber(token);
            return validateToken(token, phoneNumber);
        } catch (Exception e) {
            return false;
        }
    }

    // Extract roles from JWT token claims as a set of strings
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
