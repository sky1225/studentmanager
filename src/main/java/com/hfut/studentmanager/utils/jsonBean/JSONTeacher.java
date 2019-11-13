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

    public JSONTeacher() {
    }

    public JSONTeacher(Integer id, String number, String name, String sex, String phone, String qq) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.qq = qq;
    }

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

}
