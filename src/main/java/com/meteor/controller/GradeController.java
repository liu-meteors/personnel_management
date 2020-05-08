package com.meteor.controller;

import com.meteor.pojo.Employee;
import com.meteor.pojo.Grade;
import com.meteor.service.EmployeeService;
import com.meteor.service.GradeService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/27 21:09
 * @description：评分的Controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class GradeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    GradeService gradeService;
    @GetMapping("/getGradeEmp/{id}")
    List<Employee> getGradeEmp(@PathVariable("id") Integer id){
        Employee employee=employeeService.getEmployeeById(id);
        List<Employee> employees=new ArrayList<>();
        if (employee.getPosite()==1){
            List<Employee> peers =employeeService.getEmpByPos(1);
            List<Employee> down=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()+1);
            employees.addAll(peers);
            employees.addAll(down);

        }else if (employee.getPosite()==2){
            List<Employee> up=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()-1);
            List<Employee> peers=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite());
            List<Employee> down=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()+1);
            employees.addAll(down);
            employees.addAll(peers);
            employees.addAll(up);
        }else {
            System.out.println("获取考核人数"+employees);
            List<Employee> up=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()-1);
            List<Employee> peers=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite());
            employees.addAll(peers);
            employees.addAll(up);
        }
        employees=gradeService.setEmpGrade(employees,employee.getId());

        return employees;
    }
    @PostMapping("addGrade")
    public String addGrade(@RequestBody Grade grade){
        int isSuccess=gradeService.addGrade(grade);
        return ReturnUtils.isSuccess(isSuccess);
    }



}
