package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Exam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    @Select("select * from exam where id=#{id}")
    public Exam findExamById(Integer id);

    @Select("select * from exam")
    public List<Exam> findAllExam();

    @Select("select * from exam where clazzId=#{clazzId}")
    public List<Exam> findExamByClazzId(String clazzId);

    @Select("select * from exam where gradeId=#{gradeId}")
    public List<Exam> findExamByGradeId(String gradeId);

    @Insert("insert into exam(name, time, remark, type, gradeId, clazzId, courseId) values" +
            "(#{name}, #{time}, #{remark}, #{type}, #{gradeId}, #{clazzId}, #{courseId})")
    public boolean insertExam(Exam exam);

    @Delete("delete from exam where id=#{id}")
    public boolean deleteExamById(Integer id);

}
