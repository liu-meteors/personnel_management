package com.meteor.service.impl;

import com.meteor.mapper.AwardMapper;
import com.meteor.mapper.SalaryMapper;
import com.meteor.pojo.Award;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Grade;
import com.meteor.pojo.Salary;
import com.meteor.service.AwardService;
import com.meteor.service.EmployeeService;
import com.meteor.service.GradeService;
import com.meteor.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/29 18:51
 * @description：工资Service
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    AwardService awardService;
    @Autowired
    GradeService gradeService;
    @Autowired
    EmployeeService employeeService;

    @Override
    public int addSalary(Integer empId) {
        Salary salary=new Salary();
        salary.setEmpId(empId);
        List<Award> awards=awardService.getAllAwardByEmpNow(empId);
        List<Grade> grades=gradeService.getAllGradeByToEmp(empId);
        Employee employee=employeeService.getEmployeeById(empId);
        Float salaryMoney=0.0f;
        Float bonus=0.0f;
        Float forfeit=0.0f;
        int tempGrade=0;
        double grade=0.0;
        if (grades.size()!=0){

            for (int i=0;i<grades.size();i++){
                tempGrade+=grades.get(i).getGrade();
            }
            grade=tempGrade*1.0/grades.size();
        }
        if (awards.size()!=0){
            for (Award award:awards) {
                if (award.getAwardType()==1){
                    salaryMoney+=award.getMoney();
                    bonus+=award.getMoney();

                }else {
                    salaryMoney-=award.getMoney();
                    forfeit+=award.getMoney();
                }
            }
        }
        salaryMoney+=employee.getSalary();
        salary.setAllMoney(salaryMoney);
        salary.setBonus(bonus);
        salary.setForfeit(forfeit);
        salary.setMoney(employee.getSalary());
        salary.setPayDate(new Date());
        return salaryMapper.addSalary(salary);
    }

    @Override
    public int deleteSalaryById(Integer id) {
        return 0;
    }

    @Override
    public int updateSalary(Salary salary) {
        return 0;
    }

    @Override
    public List<Salary> getAllSalary() {
        return null;
    }

    @Override
    public List<Salary> getAllSalaryByEmp(Integer id) {
        return null;
    }

    @Override
    public Salary getSalaryByEmp(Integer id) {
        return null;
    }

    @Override
    public Salary getSalaryByEmpNow(Integer id) {
        Employee employee=employeeService.getEmployeeById(id);
        Salary salary=salaryMapper.getSalaryByEmpNow(id);
        if (salary!=null){

            salary.setEmpNumber(employee.getEmpNumber());
            salary.setName(employee.getUsername());
        }
        return salary;
    }

    @Override
    public List<Salary> getAllSalaryNow() {
        List<Salary> salaries=salaryMapper.getAllSalaryNow();
        salaries=setSalaryName(salaries);
        System.out.println("管理员端当月工资"+salaries);
        return salaries;
    }


    public List<Salary> setSalaryName(List<Salary> salaries){
        List<Employee> employees=employeeService.getAll();
        for (int i=0;i<salaries.size();i++){
            for (int j=0;j<employees.size();j++){
                if (salaries.get(i).getEmpId()==employees.get(j).getId()){
                    salaries.get(i).setName(employees.get(j).getUsername());
                    salaries.get(i).setEmpNumber(employees.get(j).getEmpNumber());
                    break;
                }
            }
        }
        return salaries;
    }
}
