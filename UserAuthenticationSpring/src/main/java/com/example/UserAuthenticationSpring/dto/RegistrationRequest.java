package com.example.UserAuthenticationSpring.dto;

import java.util.Set;


public class RegistrationRequest {
    private String userName;
    private String password;
    private Set<String> roles; //a set of role names, to be passed to the request

    public RegistrationRequest() {
    }

    public RegistrationRequest(String userName, String password, Set<String> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
