package de.bluelight.api.security.auth;

import de.bluelight.api.util.ResponseBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("X-Authorization");

        if (Strings.isBlank(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(new ResponseBuilder(HttpStatus.UNAUTHORIZED).error("No token provided!"));
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor("secureecureecureecureecuresecureecureecureecureecuresecureecureecureecureecure".getBytes()))
                    .build()
                    .parseClaimsJws(token);

            Claims body = claims.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities
                    .stream().map(map -> new SimpleGrantedAuthority(map.get("authority"))).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username,
                    null,
                    simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().write(new ResponseBuilder(HttpStatus.UNAUTHORIZED).error("Invalid token!"));
            return;
        }
        filterChain.doFilter(request, response);
    }
}
