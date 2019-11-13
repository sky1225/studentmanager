package com.hfut.studentmanager.pojo;


public class GradeCourse {

  private Integer id;
  private Integer gradeId;
  private Integer courseId;

  public GradeCourse() {
  }

  public GradeCourse(Integer id, Integer gradeId, Integer courseId) {
    this.id = id;
    this.gradeId = gradeId;
    this.courseId = courseId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
}
