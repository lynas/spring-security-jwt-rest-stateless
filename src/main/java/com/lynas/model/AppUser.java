package com.lynas.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appuser")
public class AppUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "last_password_reset")
    private Date lastPasswordReset;
    @Column(name = "authorities")
    private String authorities;

    public AppUser() {
        super();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public AppUser(String username, String password, String email, Date lastPasswordReset, String authorities) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setLastPasswordReset(lastPasswordReset);
        this.setAuthorities(authorities);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public String getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

}
