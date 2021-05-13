package de.bluelight.api.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class JwtParseResult {

    private final Claims body;
    private final String username;
    private final Set<SimpleGrantedAuthority> grantedAuthorities;

    public JwtParseResult(Claims body, String username, Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.body = body;
        this.username = username;
        this.grantedAuthorities = grantedAuthorities;
    }

    public Claims getBody() {
        return body;
    }

    public String getUsername() {
        return username;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }
}
