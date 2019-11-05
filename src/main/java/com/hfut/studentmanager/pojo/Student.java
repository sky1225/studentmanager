package com.hfut.studentmanager.pojo;


public class Student {

  private long id;
  private String number;
  private String name;
  private String sex;
  private String phone;
  private String qq;
  private long clazzId;
  private long gradeId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public long getClazzId() {
    return clazzId;
  }

  public void setClazzId(long clazzId) {
    this.clazzId = clazzId;
  }

  public long getGradeId() {
    return gradeId;
  }

  public void setGradeId(long gradeId) {
    this.gradeId = gradeId;
  }
}
