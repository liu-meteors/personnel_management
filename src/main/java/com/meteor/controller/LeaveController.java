package com.meteor.controller;

import com.meteor.pojo.Employee;
import com.meteor.pojo.Leave;
import com.meteor.service.EmployeeService;
import com.meteor.service.LeaveService;
import com.meteor.untils.ReturnUtils;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/23 15:34
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
public class LeaveController {
    @Autowired
    LeaveService leaveService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAllLeave")
    public List<Leave> getAllLeave(){
        return leaveService.getAllLeave();
    }
    @GetMapping("/getLeaveById/{id}")
    public Leave getLeaveById(@PathVariable("id") Integer id){
        return leaveService.getLeaveById(id);
    }
    @PostMapping("/leaveApply")
    public String leaveApply(@RequestBody Leave leave){
        int isSuccess=leaveService.addLeave(leave);
        return ReturnUtils.isSuccess(isSuccess);
    }
    @GetMapping("/getAllLeaveByEmp/{empId}")
    public List<Leave> getAllLeaveByEmp(@PathVariable("empId") Integer empId){
        return leaveService.getAllByEmpId(empId);
    }

    @PutMapping("/updateLeave")
    public String updateLeave(@RequestBody Leave leave){
        System.out.println(leave);
        int isSuccess=leaveService.updateLeave(leave);
        return  ReturnUtils.isSuccess(isSuccess);
    }

    @GetMapping("/getAllLeaveByEmpNow/{empId}")
    public List<Leave> getAllLeaveByEmpNow(@PathVariable("empId") Integer empId){
        return leaveService.getAllLeaveNow(empId);
    }
    @GetMapping("/getAllAdminLeaveByNow")
    public List<Leave> getAllLeaveByNow(){
        return leaveService.getAllLeaveByNow();
    }
    @GetMapping("/getAllLeaveByEmpByEmpId/{empId}")
    public List<Leave> getAllLeaveByEmpByEmpId(@PathVariable("empId") Integer empId){
        return leaveService.getAllByEmpId(empId);
    }

}
