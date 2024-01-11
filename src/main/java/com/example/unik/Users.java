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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_posts_reader",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private Set<Posts> read_posts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_posts_owner",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"))
    private Set<Posts> own_posts = new HashSet<>();

    protected Users(){
    }

}