package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Escore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EscoreMapper {

//    @Select("select * from escore where studentId=#{studentId}")
//    List<Escore> findEscoreByStudentId(Integer studentId);

    @Select("select * from escore")
    List<Escore> findAllEscore();

    @Select("select * from escore where id=#{id}")
    List<Escore> findEscoreById(Integer id);

    @Select("select * from escore where examId=#{examId} and studentId=#{studentId}")
    List<Escore> findEscoreByExamIdAndStudentId(@Param("examId") Integer examId, @Param("studentId")Integer studentId);


    @Select("select * from escore where examId=#{examId} and clazzId=#{clazzId}")
    List<Escore> findEscoreByExamIdAndClazzId(@Param("examId") Integer examId, @Param("clazzId")Integer clazzId);

    @Select("select * from escore where examId=#{examId}")
    List<Escore> findEscoreByExamId(Integer examId);

    @Select("select * from escore where clazzId=#{clazzId}")
    List<Escore> findEscoreByClazzId(Integer clazzId);

    @Select("select * from escore where courseId=#{courseId}")
    List<Escore> findEscoreByCourseId(Integer courseId);

    @Select("select * from escore where studentId=#{studentId}")
    List<Escore> findEscoreByStudentId(Integer studentId);

    @Select("select * from escore where gradeId=#{gradeId}")
    List<Escore> findEscoreByGradeId(Integer gradeId);

    @Insert("insert into escore(examId, clazzId, studentId, gradeId, courseId, score) values" +
            "(#{escore.examId}, #{escore.clazzId}, #{escore.studentId}, #{escore.gradeId}, #{escore.courseId}, #{escore.score})")
    boolean insertEscore(@Param("escore") Escore escore);

    @Delete("delete from escore where id=#{id}")
    boolean deleteEscore(Integer id);

    @Delete("delete from escore where studentId=#{studentId}")
    boolean deleteEscoreByStudentId(Integer studentId);

}
