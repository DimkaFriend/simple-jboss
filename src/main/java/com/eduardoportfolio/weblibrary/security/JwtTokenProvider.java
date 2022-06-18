package com.eduardoportfolio.weblibrary.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.eduardoportfolio.weblibrary.models.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "9a02115a835ee03d5fb83cd8a468ea33e4090aaaec87f53c9fa54512bbef4db8dc656c82a315fa0c785c08b0134716b81ddcd0153d2a7556f2e154912cf5675f";

    private int jwtExpirationInMs = 604800000;

    public String generateToken(Authentication authentication) {

    	Users userPrincipal = (Users) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
        	System.err.println(ex);
//            logger.error("Invalid JWT signature");
        	System.err.println(ex);
        } catch (MalformedJwtException ex) {
        	System.err.println(ex);
//            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	System.err.println(ex);
//            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	System.err.println(ex);
//            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	System.err.println(ex);
//            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
