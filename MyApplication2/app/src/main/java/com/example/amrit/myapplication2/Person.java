package com.example.amrit.myapplication2;

import java.io.Serializable;
//reference of the person values
public class Person implements Serializable{
    private int id;
    private String name;

    Person (int id, String name){
        this.id = id;
        this.name = name;
    }

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
}
