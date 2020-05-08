package com.meteor.controller;

import com.meteor.mapper.PromotionMapper;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Promotion;
import com.meteor.service.EmployeeService;
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

    @RequestMapping("/getEmp")
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

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
        if (i>0){
            return "success";
        }else {
            return "error";
        }
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            employee.setSignDate(DateUtils.getTrueDate(simpleDateFormat.parse(employee.getSignDateStr().split("T")[0])));
//            employee.setOverDate(DateUtils.getTrueDate(simpleDateFormat.parse(employee.getOverDateStr().split("T")[0])));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println("修改后：：：："+employee);
        Employee oldEmployee=employeeService.getEmployeeById(employee.getId());
        if (!employee.getStartToOver()[0].equals(oldEmployee.getStartToOver()[0])&&!employee.getStartToOver()[1].equals(oldEmployee.getStartToOver()[1])){
            employee.setSignDate(DateUtils.dealDateFormat(employee.getStartToOver()[0]));
            employee.setOverDate(DateUtils.dealDateFormat(employee.getStartToOver()[1]));
        }
       int isSuccess= employeeService.updateEmployee(employee);
        if (isSuccess>0){
            if (oldEmployee.getPosite()!=employee.getPosite()){
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
            }
            return "success";
        }else {
            return "error";
        }
    }
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
