package com.hfut.studentmanager.pojo;


public class ClazzCourseTeacher {

  private long id;
  private long clazzId;
  private long gradeId;
  private long courseId;
  private long teacherId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }

  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }
}
