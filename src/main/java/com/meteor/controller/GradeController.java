package com.meteor.controller;

import com.meteor.pojo.Employee;
import com.meteor.pojo.Grade;
import com.meteor.pojo.GradeHistory;
import com.meteor.service.EmployeeService;
import com.meteor.service.GradeHistoryService;
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
    @Autowired
    GradeHistoryService gradeHistoryService;
    @GetMapping("/getGradeEmp/{id}")
    List<Employee> getGradeEmp(@PathVariable("id") Integer id){
        Employee employee=employeeService.getEmployeeById(id);
        List<Employee> employees=new ArrayList<>();
        if (employee.getPosite()==1){
            List<Employee> peers =employeeService.getEmpByPos(1);
            peers.remove(0);
            List<Employee> down=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()+1);
            employees.addAll(peers);
            employees.addAll(down);

        }else if (employee.getPosite()==2){
            List<Employee> up=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()-1);
            List<Employee> peers=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite());
            List<Employee> down=employeeService.getAllEmpByDepPos(employee.getDepartment(),employee.getPosite()+1);
            up.remove(0);
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
    /**
            * @Description: 添加考核信息
            * @Param:  * @Param: grade
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    @PostMapping("/addGrade")
    public String addGrade(@RequestBody Grade grade){
        int isSuccess=gradeService.addGrade(grade);
        return ReturnUtils.isSuccess(isSuccess);
    }

    @GetMapping("/getAllGradeHistory")
    public List<GradeHistory> getAllGradeHistory(){
        return gradeHistoryService.getAllGradeHistory();
    }
    @GetMapping("/getAllGradeHistoryNow")
    public List<GradeHistory> getAllGradeHistoryNow(){
        return gradeHistoryService.getAllGradeHistoryNow();
    }
    @GetMapping("/getGradeHistoryByDep/{dep}")
    public List<GradeHistory> getGradeHistoryByDep(@PathVariable("dep") Integer dep){
        return gradeHistoryService.getGradeHistoryByDep(dep);
    }
    @GetMapping("/getGradeHistoryByDepNow/{dep}")
    public List<GradeHistory> getGradeHistoryByDepNow(@PathVariable("dep") Integer dep){
        return gradeHistoryService.getGradeHistoryByDepNow(dep);
    }




}
