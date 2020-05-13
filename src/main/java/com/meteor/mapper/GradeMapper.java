package com.meteor.mapper;

import com.meteor.pojo.Grade;
import com.meteor.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper {
    List<Grade> getAllGradeById(Integer id);
    List<Grade> getAllGradeBYDate(Integer id);
    List<Grade> getAllGradeByFromEmp(Integer id);
    List<Grade> getAllGradeByToEmp(Integer id);
    int addGrade(Grade grade);
    int deleteGrade(Integer id);
    int updateGrade(Grade grade);


}
