package com.meteor.service.impl;

import com.meteor.mapper.PositionMapper;
import com.meteor.mapper.SalaryMapper;
import com.meteor.pojo.*;
import com.meteor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionMapper positionMapper;

    /**
     * @Description: 添加工资
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addSalary(Integer empId) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());
        Calendar empCalendar = Calendar.getInstance();
        Salary salary = new Salary();
        salary.setEmpId(empId);
        //获取奖惩信息
        List<Award> awards = awardService.getAllAwardByEmpNow(empId);
        // 获取考核分数
        List<Grade> grades = gradeService.getAllGradeByToEmp(empId);
        // 获取用户信息
        Employee employee = employeeService.getEmployeeById(empId);
        empCalendar.setTime(employee.getSignDate());
        /**
         * 基本工资
         */
        Float baseMoney = 0.0f;
        // 总工资
        Float salaryMoney = 0.0f;
        // 奖金
        Float bonus = 0.0f;
        // 罚金
        Float forfeit = 0.0f;
        // 效益工资
        int tempGrade = 0;
        float grade = 0.0f;
        //绩效工资
        float gradeMoney = 0.0f;
        // 判断是不是今年之前入的值
        if (empCalendar.get(Calendar.YEAR) <= nowCalendar.get(Calendar.YEAR)) {
            // 如果是上个月入职
            int month = nowCalendar.get(Calendar.MONTH) - empCalendar.get(Calendar.MONTH);
            if (Math.abs(month) == 1 || Math.abs(month) == 11) {
                //基本工资
                baseMoney = (float) (employee.getSalary() * ((empCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - empCalendar.get(Calendar.DAY_OF_MONTH)) / (1.0 * empCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))));
                salary.setMoney(baseMoney);
                salary.setMoney(baseMoney);
            } else {
                salaryMoney += employee.getSalary();
                salary.setMoney(employee.getSalary());
            }
        }
//        System.out.println("奖惩信息："+awards);
//        System.out.println("考核分数"+grades);
        // 计算平均分
        if (grades.size() != 0) {

            for (int i = 0; i < grades.size(); i++) {
                tempGrade += grades.get(i).getGrade();
            }
            System.out.println("总分数" + tempGrade);
            grade = (float) (tempGrade * 1.0 / grades.size());
        }
        // 不同职位效益工资的基准不同
        switch (employee.getPosite()) {
            case 1:
                gradeMoney = grade * 6000 / 100;
                break;
            case 2:
                gradeMoney = grade * 4000 / 100;
                break;
            case 3:
                gradeMoney = grade * 2000 / 100;
                break;
        }
        salary.setGradeMoney(gradeMoney);
        salaryMoney += gradeMoney;
        if (awards.size() != 0) {
            for (Award award : awards) {
                // 如果是奖励
                if (award.getAwardType() == 1) {
                    salaryMoney += award.getMoney();
                    bonus += award.getMoney();
                    // 惩罚
                } else {
                    salaryMoney -= award.getMoney();
                    forfeit += award.getMoney();
                }
            }
        }

        salary.setAllMoney(salaryMoney);
        salary.setBonus(bonus);
        salary.setForfeit(forfeit);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        salary.setPayDate(calendar.getTime());
        System.out.println("工资信息" + salary);
        gradeService.deleteGrade(100);
//        return salaryMapper.addSalary(salary);
        gradeService.deleteGrade(empId);
        return salaryMapper.addSalary(salary);
    }

    @Override
    public int deleteSalaryById(Integer id) {
        return 0;
    }

    @Override
    public int updateSalary(Salary salary) {
        return salaryMapper.updateSalary(salary);
    }

    @Override
    public List<Salary> getAllSalary() {
        List<Salary> salaries = salaryMapper.getAllSalary();
        salaries = setSalaryName(salaries);
        return salaries;
    }

    /**
     * @Description: 查询某个员工的所有工资信息
     * @Param: * @Param: id 员工id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Salary> getAllSalaryByEmp(Integer id) {
        List<Salary> salaries = salaryMapper.getAllSalaryByEmp(id);
        salaries = setSalaryName(salaries);
        return salaries;
    }


    /**
     * @Description: 查询上个月的工资
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public Salary getSalaryByEmp(Integer id) {
        return salaryMapper.getSalaryByEmp(id);
    }

    @Override
    public Salary getSalaryByEmpNow(Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        Salary salary = salaryMapper.getSalaryByEmpNow(id);
        List<Department> departments = departmentService.getAll();
        List<Position> positions = positionMapper.getAllPosition();
        if (salary != null) {

            salary.setEmpNumber(employee.getEmpNumber());
            salary.setName(employee.getUsername());
            for (int k = 0; k < departments.size(); k++) {
                if (employee.getDepartment() == departments.get(k).getId()) {
                    salary.setDepartmentName(departments.get(k).getName());
                    break;
                }
            }
            for (int m = 0; m < positions.size(); m++) {
                if (employee.getPosite() == positions.get(m).getId()) {
                    salary.setPositionName(positions.get(m).getName());
                    break;
                }
            }
        }
        return salary;
    }

    @Override
    public List<Salary> getAllSalaryNow() {
        List<Salary> salaries = salaryMapper.getAllSalaryNow();
        salaries = setSalaryName(salaries);
        return salaries;
    }

    /**
     * @param empId
     * @Description: 查询员工今年的所有工资信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Salary> getSalaryByEmpIdYear(Integer empId) {
        List<Salary> salaries = salaryMapper.getSalaryByEmpIdYear(empId);
        salaries = setSalaryName(salaries);
        return salaries;
    }


    public List<Salary> setSalaryName(List<Salary> salaries) {
        List<Department> departments = departmentService.getAll();
        List<Position> positions = positionMapper.getAllPosition();
        List<Employee> employees = employeeService.getAll();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        for (int i = 0; i < salaries.size(); i++) {
            for (int j = 0; j < employees.size(); j++) {
                if (salaries.get(i).getEmpId() == employees.get(j).getId()) {
                    salaries.get(i).setName(employees.get(j).getUsername());
                    salaries.get(i).setEmpNumber(employees.get(j).getEmpNumber());
                    for (int k = 0; k < departments.size(); k++) {
                        if (employees.get(j).getDepartment() == departments.get(k).getId()) {
                            salaries.get(i).setDepartmentName(departments.get(k).getName());
                            break;
                        }
                    }
                    for (int m = 0; m < positions.size(); m++) {
                        if (employees.get(j).getPosite() == positions.get(m).getId()) {
                            salaries.get(i).setPositionName(positions.get(m).getName());
                            break;
                        }
                    }
                    break;
                }

            }
            salaries.get(i).setPayDateStr(simpleDateFormat.format(salaries.get(i).getPayDate()));

        }
        return salaries;
    }

}
