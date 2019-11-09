package com.hfut.studentmanager.utils.jsonBean;

import com.hfut.studentmanager.pojo.Course;

import java.util.List;

public class JSONTeacher {

    private Integer id;
    private String number;
    private String name;
    private String sex;
    private String phone;
    private String qq;
    private List<JSONCourse> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public List<JSONCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<JSONCourse> courses) {
        this.courses = courses;
    }
}
