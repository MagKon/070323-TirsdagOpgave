package com.example.demoa.Entity;

import com.example.demoa.Enums.Permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {
    protected String name;
    protected String password;
    protected Permission permission;
    protected List<String> todoList = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.permission = Permission.USER;
    }

    public User(String name, String password, Permission permission) {
        this.name = name;
        this.password = password;
        this.permission = Objects.requireNonNullElse(permission, Permission.USER);
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

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public List<String> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<String> todoList) {
        this.todoList = todoList;
    }

    public void addToTodoList(String todo) {
        this.todoList.add(todo);
    }

    public void removeFromTodoList(String todo) {
        this.todoList.remove(todo);
    }
}
