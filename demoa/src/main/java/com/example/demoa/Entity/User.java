package com.example.demoa.Entity;

import com.example.demoa.Enums.Permission;

public class User {
    protected String name;
    protected String password;
    protected Permission permission;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.permission = Permission.USER;
    }

    public User(String name, String password, Permission permission) {
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }
}
