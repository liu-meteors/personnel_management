package com.meteor.service.impl;

import com.meteor.mapper.GradeMapper;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Grade;
import com.meteor.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/28 18:01
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    @Override
    public List<Grade> getAllGradeById(Integer id) {
        return null;
    }

    @Override
    public List<Grade> getAllGradeBYDate(Integer id) {
        return gradeMapper.getAllGradeBYDate(id);
    }

    @Override
    public List<Grade> getAllGradeByFromEmp(Integer id) {
        return null;
    }

    @Override
    public int addGrade(Grade grade) {
        grade.setGradeDate(new Date());
        return gradeMapper.addGrade(grade);
    }

    @Override
    public int updateGrade(Grade grade) {
        return 0;
    }

    @Override
    public List<Grade> getAllGradeByToEmp(Integer id) {
        return gradeMapper.getAllGradeByToEmp(id);
    }


    public List<Employee> setEmpGrade(List<Employee> employees,Integer id){
        List<Grade> grades=getAllGradeBYDate(id);
        for (int i=0;i<employees.size();i++){
            for (int j=0;j<grades.size();j++){
                if (grades.get(j).getToGradeEmpId()==employees.get(i).getId()){
                    employees.get(i).setIsGrade("已评分");
                }
            }
            if (employees.get(i).getIsGrade()==null){
                employees.get(i).setIsGrade("未评分");
            }
            if (employees.get(i).getId()==id){
                employees.remove(i);
                i--;
            }

        }
        return employees;
    }
}
