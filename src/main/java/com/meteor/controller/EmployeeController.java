package com.meteor.controller;

import com.meteor.mapper.PromotionMapper;
import com.meteor.pojo.Admin;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Promotion;
import com.meteor.service.AdminService;
import com.meteor.service.EmployeeService;
import com.meteor.untils.AESOperator;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PromotionMapper promotionMapper;
    @Autowired
    AdminService adminService;
    /** 
            * @Description: 获取所有员工信息 
            * @Param:  * @Param: 
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @RequestMapping("/getEmp")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }
    /** 
            * @Description: 添加员工 
            * @Param:  * @Param: employee
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee){
        System.out.println(employee.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            employee.setSignDate(DateUtils.dealDateFormat(employee.getStartToOver()[0]));
            employee.setOverDate(DateUtils.dealDateFormat(employee.getStartToOver()[1]));
        System.out.println(employee.toString());
        String lastNumber=employeeService.getLastEmpNumber();
        int number=Integer.parseInt(lastNumber.substring(4))+1;
        Calendar date = Calendar.getInstance();
        String newNumber=String.valueOf(date.get(Calendar.YEAR))+String.valueOf(number);
        employee.setEmpNumber(newNumber);
        System.out.println(employee.toString());
        int i=employeeService.addEmployee(employee);
        if (employee.getPosite()==1){
           adminService.addAdmin(adminService.setAdmin(employee));
        }
        if (i>0){

            return "success";

        }else {
            return "error";
        }
    }
    /** 
            * @Description: 修改员工 
            * @Param:  * @Param: employee
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            employee.setSignDate(DateUtils.getTrueDate(simpleDateFormat.parse(employee.getSignDateStr().split("T")[0])));
//            employee.setOverDate(DateUtils.getTrueDate(simpleDateFormat.parse(employee.getOverDateStr().split("T")[0])));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Employee oldEmployee=employeeService.getEmployeeById(employee.getId());
        if (!employee.getStartToOver()[0].equals(oldEmployee.getStartToOver()[0])&&!employee.getStartToOver()[1].equals(oldEmployee.getStartToOver()[1])){
            employee.setSignDate(DateUtils.dealDateFormat(employee.getStartToOver()[0]));
            employee.setOverDate(DateUtils.dealDateFormat(employee.getStartToOver()[1]));
        }
       int isSuccess= employeeService.updateEmployee(employee);
        System.out.println("修改后：：：："+employee);
        if (isSuccess>0){
            // 如果职位发生了改变
            if (oldEmployee.getPosite()!=employee.getPosite()){
                // 改变职位为主管时
                if (employee.getPosite()==1){
                    adminService.addAdmin(adminService.setAdmin(employee));
                }
                if (oldEmployee.getPosite()==1){
                    adminService.deleteAdminById(oldEmployee.getId());
                }
                Promotion promotion=new Promotion();
                promotion.setEmpId(employee.getId());
                promotion.setOldPosition(oldEmployee.getPosite());
                promotion.setNewPosition(employee.getPosite());
                promotion.setChangeDate(new Date());
                if (oldEmployee.getPosite()<employee.getPosite()){
                    promotion.setTransferred("降职");
                }else {
                    promotion.setTransferred("升职");
                }
                promotionMapper.addPromotion(promotion);
                // 当更改主管其他信息的时候管理员信息也更新
            }else if (employee.getPosite()==1){
                //姓名更改的时候修改姓名
                System.err.println(employee);
                    Admin oldAdmin=adminService.getAdminByName(oldEmployee.getEmpNumber());
                    oldAdmin.setAdminName(employee.getUsername());
                    oldAdmin.setPassword(employee.getPassword());
                    oldAdmin.setDepId(employee.getDepartment());
                    adminService.updateAdmin(oldAdmin);
            }
            return "success";
        }else {
            return "error";
        }
    }
    /** 
            * @Description: 获取某个员工信息 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @GetMapping("/getEmpById/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
            return employeeService.getEmployeeById(id);
    }
    @GetMapping("/getEmpByEmpId/{id}")
    public Map<String,Object> getEmpByEmpId(@PathVariable("id") String empId){
        Map<String,Object> emp=new HashMap<>();
        Employee employee= employeeService.getEmployeeByNumber(empId);
        if (emp==null){
            emp.put("emp","error");
        }else {
            emp.put("emp",employee);
        }
         return  emp;
    }
    /** 
            * @Description: 删除员工 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        int isSuccess=employeeService.deleteEmployeeById(id);
        if (isSuccess>0){
            return "success";
        }else {
            return "error";
        }
    }




}
