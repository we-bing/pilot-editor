package com.github.webing.pilot.model;


import java.util.Date;

/**
 * Created by KD4 on 16. 2. 25.
 */
public class User {
    private int id;
    private String identity;
    private String isOAuth;
    private String email;
    private String name;
    private String password;
    private String userStatus;
    private String roles;
    private Date registeredAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }


    public String getIsOAuth() {
        return isOAuth;
    }

    public void setIsOAuth(String isOAuth) {
        this.isOAuth = isOAuth;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }
}
