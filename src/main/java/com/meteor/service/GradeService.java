package com.meteor.service;

import com.meteor.pojo.Employee;
import com.meteor.pojo.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getAllGradeById(Integer id);
    List<Grade> getAllGradeBYDate(Integer id);
    List<Grade> getAllGradeByFromEmp(Integer id);
    int addGrade(Grade grade);
    int updateGrade(Grade grade);
    List<Grade> getAllGradeByToEmp(Integer id);
    public List<Employee> setEmpGrade(List<Employee> employees, Integer id);
}
