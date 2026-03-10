package com.example.SaaSMultiTentBackEnd.adapter.out.persistence.security;

import com.example.SaaSMultiTentBackEnd.domain.port.out.user.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {


    private final String secretKey="50501209-498b-45da-993a-7f4a9b502a02";

    private final long expiration = 3600000;

    @Override
    public String generateToken(String email, Long companyId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() +  expiration);

        return Jwts.builder()
                .setSubject(email)
                .claim("companyId", companyId)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(
                        Keys.hmacShaKeyFor(secretKey.getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException e){
            return false;
        }
    }

    @Override
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Long getCompanyId(String token) {
        Claims claims = getClaims(token);
        return claims.get("companyId", Long.class);
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
