package com.project.testunit.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import java.util.Date;

public class JwtUtil {
    private String secretKey = "secret";  // Cambia esta clave por una más segura

    // Generar un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Nombre de usuario en el token
                .setIssuedAt(new Date())  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // Expiración del token (1 hora)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Firmado con el algoritmo HS256 y la clave secreta
                .compact();
    }

    // Obtener el nombre de usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer los claims del token (función genérica)
    private <T> T extractClaim(String token, ClaimsExtractor<T> claimsExtractor) {
        final Claims claims = extractAllClaims(token);
        return claimsExtractor.extract(claims);
    }

    private Claims extractAllClaims(String token) {
        return null;
    }
/*
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()  // Usamos el parser (en lugar de parserBuilder())
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)  // Analiza el token y obtiene los claims
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Token no válido", e);  // Manejo de error en caso de token inválido
        }
    }*/

    // Validar el token
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Verificar si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Obtener la fecha de expiración del token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Interfaz funcional para extraer claims
    @FunctionalInterface
    public interface ClaimsExtractor<T> {
        T extract(Claims claims);
    }
}
