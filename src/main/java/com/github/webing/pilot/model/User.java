package com.github.webing.pilot.model;


import java.util.Date;

/**
 * Created by KD4 on 16. 2. 25.
 */
public class User {
    private int id;
    private String identity;
    private String is_oauth;
    private String email;
    private String name;
    private String password;
    private String user_status;
    private String roles;
    private Date registered_at;

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

    public String getIs_oauth() {
        return is_oauth;
    }

    public void setIs_oauth(String is_oauth) {
        this.is_oauth = is_oauth;
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

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(Date registered_at) {
        this.registered_at = registered_at;
    }
}
