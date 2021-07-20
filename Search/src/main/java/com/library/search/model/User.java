package com.library.search.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    public User(String username, String password, String authority, Boolean enabled){
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.enabled = enabled;
    }

    public User() {

    }

    public String getUsername(){
        return username;
    }
    public boolean getEnabled(){
        return enabled;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
