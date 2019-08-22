package com.codegym.news.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{1,}[@][A-Za-z0-9]{3,}[.][A-Za-z0-9]{2,}$")
    @Column(nullable = false)
    private String email;

    @NotEmpty
    @Size(min = 4, max = 45)
    @Column(nullable = false)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 45)
    @Column(nullable = false)
    private String password;

    public User() {
    }

    public User(@NotEmpty @Pattern(regexp = "^[A-Za-z0-9]{1,}[@][A-Za-z0-9]{3,}[.][A-Za-z0-9]{2,}$") String email,
                @NotEmpty @Size(min = 4, max = 45) String username,
                @NotEmpty @Size(min = 6, max = 45) String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
