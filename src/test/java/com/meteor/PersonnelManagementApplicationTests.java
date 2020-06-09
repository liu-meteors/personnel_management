package com.meteor;

import com.meteor.controller.LoginController;
import com.meteor.controller.SalaryController;
import com.meteor.mapper.BenefitMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.mapper.SalaryMapper;
import com.meteor.pojo.*;
import com.meteor.service.*;
import com.meteor.untils.AESOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PersonnelManagementApplicationTests {
    @Autowired
    SalaryService salaryService;
    @Autowired
   GradeHistoryService gradeHistoryService;
    @Autowired
    EmployeeService employeeService;


    @Test
    void contextLoads() throws Exception {
        List<Salary> salaries=salaryService.getAllSalaryNow();
        for (Salary salary:salaries){
            int grade=0;
          Employee employee= employeeService.getEmployeeById(salary.getEmpId());
           switch (employee.getPosite()){
               case 1:
                    grade= (int) (salary.getGradeMoney()/6000*100);
                   break;
               case 2:
                   grade= (int) (salary.getGradeMoney()/4000*100);
                   break;
               case 3:
                   grade= (int) (salary.getGradeMoney()/2000*100);
                   break;
           }
           GradeHistory gradeHistory=new GradeHistory();
           gradeHistory.setGrade(grade);
           gradeHistory.setEmpId(employee.getId());
           gradeHistory.setGradeDate(new Date());
           gradeHistoryService.addGradeHistory(gradeHistory);
        }
    }


}
