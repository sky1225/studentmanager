package com.hfut.studentmanager.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Student {

  private Integer id;
  private String number;
  private String name;
  private String sex;
  private String phone;
  private String qq;
  private Integer clazzId;
  private Integer gradeId;

  public Student() {
  }

  public Student(Integer id, String number, String name, String sex, String phone, String qq, Integer clazzId, Integer gradeId) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.sex = sex;
    this.phone = phone;
    this.qq = qq;
    this.clazzId = clazzId;
    this.gradeId = gradeId;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", number='" + number + '\'' +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", phone='" + phone + '\'' +
            ", qq='" + qq + '\'' +
            ", clazzId=" + clazzId +
            ", gradeId=" + gradeId +
            '}';
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

  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }
}
