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

    /**
     * @Description: 查询所有员工的工资信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
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
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Salary getSalaryByEmp(Integer id);

    Salary getSalaryByEmpNow(Integer id);

    /**
     * @Description: 查询所有员工上个月的工资信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalaryNow();

    /**
     * @Description: 查询员工今年的所有工资信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getSalaryByEmpIdYear(Integer empId);


    /**
     * @Description: 查询部门中所有的工资信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllDepSalary(Integer dep);

    /**
     * @Description: 查询部门中所有员工上个月的工资信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getSalaryByDepNow(Integer dep);
}
