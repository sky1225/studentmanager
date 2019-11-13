package com.hfut.studentmanager.pojo;


public class Clazz {

  private Integer id;
  private String name;
  private Integer gradeId;

  public Clazz() {
  }

  public Clazz(Integer id, String name, Integer gradeId) {
    this.id = id;
    this.name = name;
    this.gradeId = gradeId;
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

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }
}
