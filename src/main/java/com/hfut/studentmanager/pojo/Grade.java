package com.hfut.studentmanager.pojo;


public class Grade {

  private Integer id;
  private String name;

  public Grade() {
  }

  public Grade(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Grade{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
