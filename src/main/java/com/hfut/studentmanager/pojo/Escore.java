package com.hfut.studentmanager.pojo;


public class Escore {

  private Integer id;
  private Integer examId;
  private Integer clazzId;
  private Integer studentId;
  private Integer gradeId;
  private Integer courseId;
  private Integer score;

  public Escore() {
  }

  public Escore(Integer id, Integer examId, Integer clazzId, Integer studentId, Integer gradeId, Integer courseId, Integer score) {
    this.id = id;
    this.examId = examId;
    this.clazzId = clazzId;
    this.studentId = studentId;
    this.gradeId = gradeId;
    this.courseId = courseId;
    this.score = score;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getExamId() {
    return examId;
  }

  public void setExamId(Integer examId) {
    this.examId = examId;
  }

  public Integer getClazzId() {
    return clazzId;
  }

  public void setClazzId(Integer clazzId) {
    this.clazzId = clazzId;
  }

  public Integer getStudentId() {
    return studentId;
  }

  public void setStudentId(Integer studentId) {
    this.studentId = studentId;
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

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
