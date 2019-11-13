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

    @Select("select * from exam where courseId=#{courseId}")
    List<Exam> findExamByCourseId(Integer courseId);

    @Select("select * from exam where id=#{id}")
    Exam findExamById(Integer id);

    @Select("select * from exam")
    List<Exam> findAllExam();

    @Select("select * from exam where clazzId=#{clazzId}")
    List<Exam> findExamByClazzId(String clazzId);

    @Select("select * from exam where gradeId=#{gradeId}")
    List<Exam> findExamByGradeId(Integer gradeId);

    @Insert("insert into exam(name, time, remark, type, gradeId, clazzId, courseId) values" +
            "(#{name}, #{time}, #{remark}, #{type}, #{gradeId}, #{clazzId}, #{courseId})")
    boolean insertExam(Exam exam);

    @Delete("delete from exam where id=#{id}")
    boolean deleteExamById(Integer id);

}
