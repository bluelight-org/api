package de.bluelight.api.entities;

import javax.validation.constraints.NotBlank;

public class CreateProfileDto {

    @NotBlank(message = "Name must not be empty!")
    private String name;
    @NotBlank(message = "Email must not be empty!")
    private String email;
    @NotBlank(message = "Password must not be empty!")
    private String password;

    public CreateProfileDto() {
    }

    public CreateProfileDto(String name, String email, String password) {
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
