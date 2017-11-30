package com.example.lingarajsajjan.studentviolation.model;

/**
 * Created by lalit on 9/12/2016.
 */
public class UserCreation {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gendar;
    private String userType;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGendar(){ return gendar;}
    public void setGendar(String gendar){this.gendar=gendar;}


    public String getUserType(){return userType;}
    public void setUserType(String userType){this.userType=userType;}

}
