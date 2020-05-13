package com.meteor.controller;

import com.meteor.pojo.Salary;
import com.meteor.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/29 21:42
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class SalaryController {
    @Autowired
    SalaryService salaryService;
    @GetMapping("/getSalaryByEmpNow/{id}")
    public List<Salary> getSalaryByEmpNow(@PathVariable("id") Integer id){
        Salary salary=salaryService.getSalaryByEmpNow(id);
        System.out.println(salary);
        List<Salary> salaries=new ArrayList<>();
        salaries.add(salary);
        return salaries;
    }

    @GetMapping("/getAllSalary")
    public List<Salary> getAllSalary(){
        return salaryService.getAllSalary();
    }


    @GetMapping("/getAllSalaryNow")
    public List<Salary> getAllSalaryNow(){
        return salaryService.getAllSalaryNow();
    }
}
