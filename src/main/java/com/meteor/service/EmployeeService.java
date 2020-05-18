package com.meteor.service;

import com.meteor.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * @Description: 获取全部员工
     * @Param:  * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Employee> getAll();
    /**
     * @Description: 根据工号查询员工
     * @Param:  * @Param: number
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Employee getEmployeeByNumber(String number);
    /**
     * @Description: 获取员工数量
     * @Param:  * @Param: depId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Integer getCount(Integer depId);
    /**
     * @Description: 添加员工
     * @Param:  * @Param: employee
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int addEmployee(Employee employee);
    /**
     * @Description:  删除员工
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int deleteEmployeeById(Integer id);
    /**
     * @Description: 修改员工信息
     * @Param:  * @Param: employee
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int updateEmployee(Employee employee);
    /**
     * @Description: 根据id查询员工
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Employee getEmployeeById(Integer id);
    /**
     * @Description:  返回最后一个员工的工号
     * @Param:  * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    String getLastEmpNumber();
    List<Employee> getAllEmpByDepPos(Integer dep,Integer pos);
    List<Employee> getEmpByPos(Integer pos);


    /**
     * @Description: 根据部门查询员工
     * @Param: * @Param: pos
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Employee> getEmpByDep(Integer dep);
}
