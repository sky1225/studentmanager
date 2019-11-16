package com.hfut.studentmanager.utils.jsonBean;

public class Score {
    private Integer studentId;
    private String score;
    private String number;
    private String name;
    private Integer examId;

    @Override
    public String toString() {
        return "Score{" +
                "studentId=" + studentId +
                ", score='" + score + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", examId=" + examId +
                '}';
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }
}
