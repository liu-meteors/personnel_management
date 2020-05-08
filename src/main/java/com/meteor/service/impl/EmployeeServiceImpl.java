package com.meteor.service.impl;

import com.meteor.mapper.DepartmentMapper;
import com.meteor.mapper.EmployeeMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Department;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Position;
import com.meteor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;
    @Override
    public List<Employee> getAll() {
        List<Employee> employees=employeeMapper.getAll();
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (int i=0;i<employees.size();i++){
            for (int j=0;j<departments.size();j++){
                if (employees.get(i).getDepartment()==departments.get(j).getId()){
                    employees.get(i).setDepartmentName(departments.get(j).getName());
                    break;
                }
            }
            for (int k=0;k<positions.size();k++){
                if (employees.get(i).getPosite()==positions.get(k).getId()){
                    employees.get(i).setPositionName(positions.get(k).getName());
                    break;
                }
            }
            employees.get(i).setSignDateStr(simpleDateFormat.format( employees.get(i).getSignDate()));
            employees.get(i).setOverDateStr(simpleDateFormat.format( employees.get(i).getOverDate()));
        }
        return employees;
    }

    /**
     * @param number
     * @Description: 根据工号查询员工
     * @Param: * @Param: number
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public Employee getEmployeeByNumber(String number) {
        Employee employee=employeeMapper.getEmployeeByNumber(number);
        if (employee==null){
            return null;
        }
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int j=0;j<departments.size();j++){
            if (employee.getDepartment()==departments.get(j).getId()){
                employee.setDepartmentName(departments.get(j).getName());
                break;
            }
        }
        for (int k=0;k<positions.size();k++){
            if (employee.getPosite()==positions.get(k).getId()){
                employee.setPositionName(positions.get(k).getName());
                break;
            }
        }
        employee.setSignDateStr(simpleDateFormat.format(employee.getSignDate()));
        employee.setOverDateStr(simpleDateFormat.format(employee.getOverDate()));
        System.out.println("修改：：："+employee.toString());
        return employee;
    }

    @Override
    public Integer getCount(Integer depId) {
        return employeeMapper.getCount(depId);
    }

    /**
     * @param employee
     * @Description: 添加员工
     * @Param: * @Param: employee
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.addEmployee(employee);
    }

    /**
     * @param id
     * @Description: 删除员工
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteEmployeeById(Integer id) {
        return employeeMapper.deleteEmployeeById(id);
    }

    /**
     * @param employee
     * @Description: 修改员工信息
     * @Param: * @Param: employee
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * @param id
     * @Description: 根据id查询员工
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee=employeeMapper.getEmployeeById(id);
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int j=0;j<departments.size();j++){
            if (employee.getDepartment()==departments.get(j).getId()){
                employee.setDepartmentName(departments.get(j).getName());
                break;
            }
        }
        for (int k=0;k<positions.size();k++){
            if (employee.getPosite()==positions.get(k).getId()){
                employee.setPositionName(positions.get(k).getName());
                break;
            }
        }

        employee.setSignDateStr(simpleDateFormat.format(employee.getSignDate()));
        employee.setOverDateStr(simpleDateFormat.format(employee.getOverDate()));
        String[] strings={employee.getSignDateStr(),employee.getOverDateStr()};
        employee.setStartToOver(strings);
        System.out.println("修改：：："+employee.toString());
        return employee;
    }

    /**
     * @Description: 返回最后一个员工的工号
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public String getLastEmpNumber() {
        return employeeMapper.getLastEmpNumber();
    }

    @Override
    public List<Employee> getAllEmpByDepPos(Integer dep, Integer pos) {
        List<Employee> employees=employeeMapper.getAllEmpByDepPos(dep,pos);
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (int i=0;i<employees.size();i++){
            for (int j=0;j<departments.size();j++){
                if (employees.get(i).getDepartment()==departments.get(j).getId()){
                    employees.get(i).setDepartmentName(departments.get(j).getName());
                    break;
                }
            }
            for (int k=0;k<positions.size();k++){
                if (employees.get(i).getPosite()==positions.get(k).getId()){
                    employees.get(i).setPositionName(positions.get(k).getName());
                    break;
                }
            }
            employees.get(i).setSignDateStr(simpleDateFormat.format( employees.get(i).getSignDate()));
            employees.get(i).setOverDateStr(simpleDateFormat.format( employees.get(i).getOverDate()));
        }
        return employees;
    }

    @Override
    public List<Employee> getEmpByPos(Integer pos) {
        List<Employee> employees=employeeMapper.getEmpByPos(pos);
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        for (int i=0;i<employees.size();i++){
            for (int j=0;j<departments.size();j++){
                if (employees.get(i).getDepartment()==departments.get(j).getId()){
                    employees.get(i).setDepartmentName(departments.get(j).getName());
                    break;
                }
            }
            for (int k=0;k<positions.size();k++){
                if (employees.get(i).getPosite()==positions.get(k).getId()){
                    employees.get(i).setPositionName(positions.get(k).getName());
                    break;
                }
            }
            employees.get(i).setSignDateStr(simpleDateFormat.format( employees.get(i).getSignDate()));
            employees.get(i).setOverDateStr(simpleDateFormat.format( employees.get(i).getOverDate()));
        }
        return employees;
    }
}
