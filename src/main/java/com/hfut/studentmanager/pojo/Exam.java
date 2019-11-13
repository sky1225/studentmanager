package com.hfut.studentmanager.pojo;


import java.util.Date;

public class Exam {

  private Integer id;
  private String name;
  private Date time;
  private String remark;
  private Integer type;
  private Integer gradeId;
  private Integer clazzId;
  private Integer courseId;

  public Exam() {
  }

  public Exam(Integer id, String name, Date time, String remark, Integer type, Integer gradeId, Integer clazzId, Integer courseId) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.remark = remark;
    this.type = type;
    this.gradeId = gradeId;
    this.clazzId = clazzId;
    this.courseId = courseId;
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

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getGradeId() {
    return gradeId;
  }

  public void setGradeId(Integer gradeId) {
    this.gradeId = gradeId;
  }

  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }
}
