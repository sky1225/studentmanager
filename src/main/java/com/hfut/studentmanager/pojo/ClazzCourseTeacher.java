package com.hfut.studentmanager.pojo;


public class ClazzCourseTeacher {

  private Integer id;
  private Integer clazzId;
  private Integer gradeId;
  private Integer courseId;
  private Integer teacherId;

  public ClazzCourseTeacher() {
  }

  public ClazzCourseTeacher(Integer id, Integer clazzId, Integer gradeId, Integer courseId, Integer teacherId) {
    this.id = id;
    this.clazzId = clazzId;
    this.gradeId = gradeId;
    this.courseId = courseId;
    this.teacherId = teacherId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getCourseId() {
    return courseId;
  }

  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  public Integer getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(Integer teacherId) {
    this.teacherId = teacherId;
  }
}
