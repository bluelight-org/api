package de.bluelight.api.security.auth;

import de.bluelight.api.security.jwt.JwtParseResult;
import de.bluelight.api.security.jwt.JwtUtil;
import de.bluelight.api.util.ResponseBuilder;
import io.jsonwebtoken.JwtException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTVerifier extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JWTVerifier(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

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
            JwtParseResult jwt = jwtUtil.parse(authorizationHeader);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    jwt.getUsername(),
                    null,
                    jwt.getGrantedAuthorities()
            );
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
