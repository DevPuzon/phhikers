package com.softwaresolution.phhikers.Pojo;

public class User {
    String email;
    String password;
    String name;
    String contact;
    String uid;

    public User() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(String email, String password, String name, String contact, String uid) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.uid = uid;
    }
}
