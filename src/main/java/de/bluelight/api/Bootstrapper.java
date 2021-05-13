package de.bluelight.api;

import de.bluelight.api.security.jwt.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Bootstrapper {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrapper.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public JwtUtil jwtService() {
        return new JwtUtil();
    }

}
