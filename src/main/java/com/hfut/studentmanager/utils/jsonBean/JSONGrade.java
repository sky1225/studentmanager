package com.hfut.studentmanager.utils.jsonBean;

import java.util.Arrays;

public class JSONGrade {

    private Integer id;
    private String name;
    private String[] courses;

    @Override
    public String toString() {
        return "JSONGrade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courses=" + Arrays.toString(courses) +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}
