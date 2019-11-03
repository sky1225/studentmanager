package com.hfut.studentmanager.pojo;


public class ClazzCourseTeacher {

  private long id;
  private long clazzid;
  private long gradeid;
  private long courseid;
  private long teacherid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getClazzid() {
    return clazzid;
  }

  public void setClazzid(long clazzid) {
    this.clazzid = clazzid;
  }


  public long getGradeid() {
    return gradeid;
  }

  public void setGradeid(long gradeid) {
    this.gradeid = gradeid;
  }


  public long getCourseid() {
    return courseid;
  }

  public void setCourseid(long courseid) {
    this.courseid = courseid;
  }


  public long getTeacherid() {
    return teacherid;
  }

  public void setTeacherid(long teacherid) {
    this.teacherid = teacherid;
  }

}
