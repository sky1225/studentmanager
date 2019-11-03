package com.hfut.studentmanager.pojo;


public class System {

  private long id;
  private String schoolName;
  private long forbidTeacher;
  private long forbidStudent;
  private String noticeTeacher;
  private String noticeStudent;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }


  public long getForbidTeacher() {
    return forbidTeacher;
  }

  public void setForbidTeacher(long forbidTeacher) {
    this.forbidTeacher = forbidTeacher;
  }


  public long getForbidStudent() {
    return forbidStudent;
  }

  public void setForbidStudent(long forbidStudent) {
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
