package com.meteor;

import com.meteor.controller.LoginController;
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
    LoginController loginController;
    @Autowired
    SalaryService salaryService;
    @Autowired
    EmployeeService employeeService;

    @Test
    void contextLoads() throws Exception {
        loginController.setSalary();
//        List<Employee> employees=employeeService.getAll();
//        for (Employee employee: employees){
//            Salary salary=salaryService.getSalaryByEmp(employee.getId());
//            if (salary!=null){
//                System.err.println(employee.getUsername());
//            }
//        }
    }


}
