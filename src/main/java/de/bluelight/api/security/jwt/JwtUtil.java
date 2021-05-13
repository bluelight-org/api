package de.bluelight.api.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationDate}")
    private long expirationDate;

    public JwtGenerationResult generateRefreshToken(Authentication authResult) {
        Date date = new Date(System.currentTimeMillis() + expirationDate);
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
        return new JwtGenerationResult(token, date);
    }

    @SuppressWarnings("unchecked")
    public JwtParseResult parse(String header) throws JwtException {
        String token = header.replace("Bearer ", "");
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token);
        Claims body = claims.getBody();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities
                .stream().map(map -> new SimpleGrantedAuthority(map.get("authority"))).collect(Collectors.toSet());
        return new JwtParseResult(body, body.getSubject(), simpleGrantedAuthorities);
    }
}
