package com.hfut.studentmanager.pojo;


public class Escore {

  private long id;
  private long examId;
  private long clazzId;
  private long studentId;
  private long gradeId;
  private long courseId;
  private long score;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }

  public long getClazzId() {
    return clazzId;
  }

  public void setClazzId(long clazzId) {
    this.clazzId = clazzId;
  }

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
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

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }
}
