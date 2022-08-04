package com.mrg.ecommercemvvmretrofit.Models;

import java.io.Serializable;

public class User implements Serializable {
    String email;
    String name;
    String password;
    String role;
    String avatar;
    int id;
    String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User(String access_token) {
        this.access_token = access_token;
    }

    public String getEmail() {
        return email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User( String name,String email, String password, String avatar, String role, int id) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
        this.id = id;
    }

    public User(String email, String name, String password, String role, String avatar) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
    }

    public User() {

    }

    public User(String email, String name, String password, String avatar) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
    }
}
