package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract  class User {
    protected String userId;
    protected String role;
    protected String username;
    protected String email;
    protected String passwordHash;
    
    
    public User(String userId,String role,String username,String email,String passwordHash){
        this.userId= userId;
        this.role=role;
        this.username=username;
        this.email=email;
        this.passwordHash=passwordHash;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }


    public JSONObject toJsonObject() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("userId", this.getUserId());
    jsonObject.put("role", this.getRole());
    jsonObject.put("username", this.getUsername());
    jsonObject.put("email", this.getEmail());
    jsonObject.put("passwordHash", this.getPasswordHash());
    return jsonObject;
}

}

