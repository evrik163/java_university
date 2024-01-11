package com.example.unik;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Users {
    @Id
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Setter
    @Column(name="password", nullable = false)
    private String password;

    @Setter
    @Column(name="role", nullable = false)
    private String role;

    protected Users(){
    }

}