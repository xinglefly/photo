package com.xinglefly.photo.bean;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Course implements Serializable {

    private String courcse_id;
    private String name;

    public String getCourcse_id() {
        return courcse_id;
    }

    public void setCourcse_id(String courcse_id) {
        this.courcse_id = courcse_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course(String courcse_id, String name) {
        this.courcse_id = courcse_id;
        this.name = name;
    }
}