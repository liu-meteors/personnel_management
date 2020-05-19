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

    /**
     * @Description: 获取员工上各个月的工资信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getSalaryByEmpNow/{id}")
    public List<Salary> getSalaryByEmpNow(@PathVariable("id") Integer id) {
        Salary salary = salaryService.getSalaryByEmpNow(id);
        System.out.println(salary);
        List<Salary> salaries = new ArrayList<>();
        salaries.add(salary);
        return salaries;
    }

    /**
     * @Description: 获取所有工资信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllSalary")
    public List<Salary> getAllSalary() {
        return salaryService.getAllSalary();
    }

    /**
     * @Description: 获取所有公司上个月的工资信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllSalaryNow")
    public List<Salary> getAllSalaryNow() {
        return salaryService.getAllSalaryNow();
    }





    @GetMapping("/getAllSalaryByEmpId/{id}")
    public List<Salary> getAllSalaryByEmpId(@PathVariable("id") Integer id) {
        return salaryService.getAllSalaryByEmp(id);
    }



    /**
     * @Description: 员工年工资信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getSalaryByEmpIdYear/{empId}")
    public List<Salary> getSalaryByEmpIdYear(@PathVariable("empId") Integer empId) {
        return salaryService.getSalaryByEmpIdYear(empId);
    }

    /**
            * @Description: 查询部门所有工资信息
            * @Param:  * @Param: dep
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    @GetMapping("/getAllDepSalary/{dep}")
    public List<Salary> getAllDepSalary(@PathVariable("dep") Integer dep){
        return salaryService.getAllDepSalary(dep);
    }

    @GetMapping("/getSalaryByDepNow/{dep}")
    public List<Salary> getSalaryByDepNow(@PathVariable("dep") Integer dep){
        return salaryService.getSalaryByDepNow(dep);
    }
}
