package com.xinglefly.photo.bean;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class Student implements Serializable{

    private String uid;
    private String name;
    private List<Course> courseList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Student(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Student(String uid, String name, List<Course> courseList) {
        this.uid = uid;
        this.name = name;
        this.courseList = courseList;
    }
}