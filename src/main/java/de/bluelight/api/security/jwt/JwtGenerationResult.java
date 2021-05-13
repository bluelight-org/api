package de.bluelight.api.security.jwt;

import java.util.Date;

public class JwtGenerationResult {

    private final String token;
    private final Date expirationDate;

    public JwtGenerationResult(String token, Date expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
