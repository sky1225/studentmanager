package com.hfut.studentmanager.mapper;

import com.hfut.studentmanager.pojo.Escore;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EscoreMapper {

    @Select("select * from escore")
    public List<Escore> findAllEscore();

    @Select("select * from escore where id=#{id}")
    public List<Escore> findEscoreById(Integer id);

    @Select("select * from escore where examId=#{examId}")
    public List<Escore> findEscoreByExamId(Integer examId);

    @Select("select * from escore where clazzId=#{clazzId}")
    public List<Escore> findEscoreByClazzId(Integer clazzId);

    @Select("select * from escore where courseId=#{courseId}")
    public List<Escore> findEscoreByCourseId(Integer courseId);

    @Select("select * from escore where studentId=#{studentId}")
    public List<Escore> findEscoreByStudentId(Integer studentId);

    @Select("select * from escore where gradeId=#{gradeId}")
    public List<Escore> findEscoreByGradeId(Integer gradeId);

    @Select("insert into escore(examId, clazzId, studentId, gradeId, courseId, score) values" +
            "(#{examId}, #{clazzId}, #{studentId}, #{gradeId}, #{courseId}, #{score})")
    public boolean insertEscore(Escore escore);

    @Delete("delete from escore where id=#{id}")
    public boolean deleteEscore(Integer id);

}
