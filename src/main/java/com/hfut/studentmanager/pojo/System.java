package com.hfut.studentmanager.pojo;


public class System {

  private Integer id;
  private String schoolName;
  private Integer forbidTeacher;
  private Integer forbidStudent;
  private String noticeTeacher;
  private String noticeStudent;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public Integer getForbidTeacher() {
    return forbidTeacher;
  }

  public void setForbidTeacher(Integer forbidTeacher) {
    this.forbidTeacher = forbidTeacher;
  }

  public Integer getForbidStudent() {
    return forbidStudent;
  }

  public void setForbidStudent(Integer forbidStudent) {
    this.forbidStudent = forbidStudent;
  }

  public String getNoticeTeacher() {
    return noticeTeacher;
  }

  public void setNoticeTeacher(String noticeTeacher) {
    this.noticeTeacher = noticeTeacher;
  }

  public String getNoticeStudent() {
    return noticeStudent;
  }

  public void setNoticeStudent(String noticeStudent) {
    this.noticeStudent = noticeStudent;
  }
}
