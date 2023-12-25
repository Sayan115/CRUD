package com.example.crud.model;

public class Contact {
    private int id;
    private String name;
    private String phone_no;

    public Contact(int id, String name, String phone_no) {
        this.id = id;
        this.name = name;
        this.phone_no = phone_no;
    }

    public Contact(String name, String phone_no) {
        this.name = name;
        this.phone_no = phone_no;
    }

    public Contact(){}

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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
