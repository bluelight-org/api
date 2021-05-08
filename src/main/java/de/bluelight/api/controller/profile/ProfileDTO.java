package de.bluelight.api.controller.profile;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ProfileDTO {

    @NotBlank(message = "Name must not be empty!")
    private String name;
    @NotBlank(message = "Email must not be empty!")
    private String email;
    @NotBlank(message = "Password must not be empty!")
    private String password;

    public ProfileDTO() {
    }

    public ProfileDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
