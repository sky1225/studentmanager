package com.hfut.studentmanager.pojo;


import java.sql.Date;

public class Exam {

  private long id;
  private String name;
  private java.sql.Date time;
  private String remark;
  private long type;
  private long gradeId;
  private long clazzId;
  private long courseId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }

  public long getGradeId() {
    return gradeId;
  }

  public void setGradeId(long gradeId) {
    this.gradeId = gradeId;
  }

  public long getClazzId() {
    return clazzId;
  }

  public void setClazzId(long clazzId) {
    this.clazzId = clazzId;
  }

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }
}
