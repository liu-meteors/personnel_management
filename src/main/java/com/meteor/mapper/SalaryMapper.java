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

    /**
     * @Description: 获取所有员工的所有工资信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalary();

    /**
     * @Description: 获取该员工所有的工资信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalaryByEmp(Integer id);

    /**
     * @Description: 查询所有员工上个月的工资
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalaryNow();

    /**
     * @Description: 查询上个月的工资
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Salary getSalaryByEmp(Integer id);

    /**
     * @Description: 查询某个员工上个月的工资
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    Salary getSalaryByEmpNow(Integer id);

    /**
     * @Description: 查询员工今年的所有工资信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getSalaryByEmpIdYear(Integer empId);

    /**
     * @Description: 获取部门所有员工工资信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getAllSalaryByDep(Integer dep);

    /**
     * @Description: 获取部门所有员工上个月工资
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Salary> getSalaryByDepNow(Integer dep);
}
