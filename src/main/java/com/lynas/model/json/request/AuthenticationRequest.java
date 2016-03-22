package com.lynas.model.json.request;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class AuthenticationRequest {

    private static final long serialVersionUID = 6624726180748515507L;
    private String username;
    private String password;

    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


}
