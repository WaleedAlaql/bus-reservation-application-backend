package com.waleed.BusReservation.security;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecretKey;

    @Value("${app.jwt.expiration.milliseconds}")
    private Long jwtExpiration;

    /**
     * Generates a JWT token for the authenticated user.
     * 
     * @param authentication The Authentication object containing user details
     * @return A JWT token as a String
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpiration);

        // Build the JWT token with subject (username), issuance date, expiration date, and signature
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    /**
     * Extracts the username from a given JWT token.
     * 
     * @param token The JWT token
     * @return The username stored in the token's claims
     */
    public String getUsernameFromJwt(String token) {
        // Parse the JWT token and extract the claims
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Return the subject claim, which contains the username
        return claims.getSubject();
    }

    /**
     * Validates the given JWT token.
     * 
     * @param token The JWT token to validate
     * @return true if the token is valid, otherwise throws an exception
     * @throws BadCredentialsException if the token is invalid, expired, unsupported, or empty
     */
    public boolean validateToken(String token) {
        try {
            // Attempt to parse the token. If successful, the token is valid.
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new BadCredentialsException("Invalid token", e);
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Token expired", e);
        } catch (UnsupportedJwtException e) {
            throw new BadCredentialsException("Unsupported token", e);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Token is empty", e);
        }
    }

    /**
     * Creates a cryptographic key from the base64-encoded secret key.
     * 
     * @return A Key object used for signing and verifying JWT tokens
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }
}
