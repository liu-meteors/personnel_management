package com.meteor.service;

import com.meteor.pojo.Salary;

import java.util.List;

public interface SalaryService {
    /**
     * @Description: 添加工资
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int addSalary(Integer empId);
    int deleteSalaryById(Integer id);
    int updateSalary(Salary salary);
    List<Salary> getAllSalary();
    /**
     * @Description: 查询某个员工的所有工资信息
     * @Param: * @Param: id 员工id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalaryByEmp(Integer id);
    /**
     * @Description: 查询上个月的工资
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Salary getSalaryByEmp(Integer id);
    Salary getSalaryByEmpNow(Integer id);
    List<Salary> getAllSalaryNow();

    /**
     * @Description: 查询员工今年的所有工资信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getSalaryByEmpIdYear(Integer empId);
}
