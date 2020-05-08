package com.meteor.mapper;

import com.meteor.pojo.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalaryMapper {
    int addSalary(Salary salary);
    int deleteSalaryById(Integer id);
    int updateSalary(Salary salary);
    List<Salary> getAllSalary();
    List<Salary> getAllSalaryByEmp(Integer id);
    List<Salary> getAllSalaryNow();
    Salary getSalaryByEmp(Integer id);
    Salary getSalaryByEmpNow(Integer id);
}
