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

    /**
     * @Description: 获取所有请假信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllLeave")
    public List<Leave> getAllLeave() {
        return leaveService.getAllLeave();
    }

    /**
     * @Description: 获取某个请假信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getLeaveById/{id}")
    public Leave getLeaveById(@PathVariable("id") Integer id) {
        return leaveService.getLeaveById(id);
    }

    /**
     * @Description: 添加请假申请
     * @Param: * @Param: leave
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @PostMapping("/leaveApply")
    public String leaveApply(@RequestBody Leave leave) {
        int isSuccess = leaveService.addLeave(leave);
        return ReturnUtils.isSuccess(isSuccess);
    }

    /**
     * @Description: 获取某个员工的请假信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllLeaveByEmp/{empId}")
    public List<Leave> getAllLeaveByEmp(@PathVariable("empId") Integer empId) {
        return leaveService.getAllByEmpId(empId);
    }

    /**
     * @Description: 修改请假信息
     * @Param: * @Param: leave
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @PutMapping("/updateLeave")
    public String updateLeave(@RequestBody Leave leave) {
        System.out.println(leave);
        int isSuccess = leaveService.updateLeave(leave);
        return ReturnUtils.isSuccess(isSuccess);
    }

    /**
     * @Description: 获取员工当前月的请假信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllLeaveByEmpNow/{empId}")
    public List<Leave> getAllLeaveByEmpNow(@PathVariable("empId") Integer empId) {
        return leaveService.getAllLeaveNow(empId);
    }

    /**
     * @Description: 获得所有当前月的请假信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllAdminLeaveByNow")
    public List<Leave> getAllLeaveByNow() {
        return leaveService.getAllLeaveByNow();
    }

    @GetMapping("/getAllLeaveByEmpByEmpId/{empId}")
    public List<Leave> getAllLeaveByEmpByEmpId(@PathVariable("empId") Integer empId) {
        return leaveService.getAllByEmpId(empId);
    }
    /**
            * @Description: 查看所有部门请假信息
            * @Param:  * @Param: dep
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    @GetMapping("/getAllDepLeave/{dep}")
    public List<Leave> getAllDepLeave(@PathVariable("dep") Integer dep){
        return leaveService.getAllDepLeave(dep);
    }

    @GetMapping("/getLeaveByDepMonth/{dep}")
    public List<Leave> getLeaveByDepMonth(@PathVariable("dep") Integer dep){
        return leaveService.getLeaveByDepMonth(dep);
    }

}
