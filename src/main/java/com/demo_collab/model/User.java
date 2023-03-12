package com.demo_collab.model;

public class User {
    private int user_id;
    private String user_name;
    private String phone;
    private int province_id;
    private int role_id;

    public User() {
    }

    public User(int user_id, String user_name, String phone, int province_id, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.phone = phone;
        this.province_id = province_id;
        this.role_id = role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
