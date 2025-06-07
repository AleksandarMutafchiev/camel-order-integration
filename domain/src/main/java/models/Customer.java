package models;

import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Customer {

    private UUID id;

    @NotNull
    private String name;

    @Email
    private String email;

    public Customer() {}

    public Customer(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public @NotNull UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
