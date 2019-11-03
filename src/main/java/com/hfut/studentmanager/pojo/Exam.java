package com.hfut.studentmanager.pojo;


public class Exam {

  private long id;
  private String name;
  private java.sql.Date time;
  private String remark;
  private long type;
  private long gradeid;
  private long clazzid;
  private long courseid;


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


  public java.sql.Date getTime() {
    return time;
  }

  public void setTime(java.sql.Date time) {
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


  public long getGradeid() {
    return gradeid;
  }

  public void setGradeid(long gradeid) {
    this.gradeid = gradeid;
  }


  public long getClazzid() {
    return clazzid;
  }

  public void setClazzid(long clazzid) {
    this.clazzid = clazzid;
  }


  public long getCourseid() {
    return courseid;
  }

  public void setCourseid(long courseid) {
    this.courseid = courseid;
  }

}
