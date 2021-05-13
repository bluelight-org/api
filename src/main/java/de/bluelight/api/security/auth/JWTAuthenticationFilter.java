package de.bluelight.api.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bluelight.api.entities.LoginDto;
import de.bluelight.api.security.jwt.JwtGenerationResult;
import de.bluelight.api.security.jwt.JwtUtil;
import de.bluelight.api.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword())
            );
        } catch (IOException ignored) {
            response.setContentType("application/json");
            response.setStatus(400);
            try {
                response.getWriter().write(new ResponseBuilder(HttpStatus.BAD_REQUEST).error("No credentials provided!"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(new ResponseBuilder(HttpStatus.UNAUTHORIZED).error("Bad credentials!"));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        JwtGenerationResult jwt = jwtUtil.generateRefreshToken(authResult);
        response.setContentType("application/json");
        response.getWriter().write(new ResponseBuilder(HttpStatus.OK).data(
                Map.of("refreshToken", jwt.getToken(), "expiresAt", jwt.getExpirationDate().getTime()))
        );
    }
}
