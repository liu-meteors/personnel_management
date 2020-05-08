package com.meteor.service;

import com.meteor.pojo.Salary;

import java.util.List;

public interface SalaryService {
    int addSalary(Integer empId);
    int deleteSalaryById(Integer id);
    int updateSalary(Salary salary);
    List<Salary> getAllSalary();
    List<Salary> getAllSalaryByEmp(Integer id);
    Salary getSalaryByEmp(Integer id);
    Salary getSalaryByEmpNow(Integer id);
    List<Salary> getAllSalaryNow();
}
