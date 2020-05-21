package com.meteor.controller;

import com.meteor.pojo.Admin;
import com.meteor.pojo.Employee;
import com.meteor.pojo.ManagerSessions;
import com.meteor.pojo.Salary;
import com.meteor.service.AdminService;
import com.meteor.service.EmployeeService;
import com.meteor.service.SalaryService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@CrossOrigin(allowCredentials = "true")
public class LoginController {
    private Map<String, HttpSession> sessions=new HashMap<String,HttpSession>();

    @Autowired
    AdminService adminService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    SalaryService salaryService;


    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> map,HttpSession session)  {
        Map<String,Object> reposeMap=new HashMap<>();
        String username= (String) map.get("username");
        String password= (String) map.get("password");
        String identity= (String) map.get("identity");
        setSalary();
        if (identity.equals("admin")){
            Admin admin=adminService.getAdminByName(username);
            if (admin!=null){


            Employee employee=employeeService.getEmployeeById(admin.getEmpId());
            if (admin!=null){

                if (password.equals(admin.getPassword())){
                    session.setAttribute("user",username);
                    sessions.put("admin"+admin.getId(),session);
                    System.out.println("登录成功");
                    reposeMap.put("code","200");
                    reposeMap.put("user",admin.getAdminName());
                    reposeMap.put("id",admin.getId());
                    reposeMap.put("dep",admin.getDepId());
                    reposeMap.put("identity","admin");
                }else {
                    reposeMap.put("code","403");
                }
            }else {
                reposeMap.put("code","403");
            }
            }else {
                reposeMap.put("code","403");
            }
        }else {
            Employee employee=employeeService.getEmployeeByNumber(username);
            if (employee!=null){
                System.out.println("员工："+employee.toString());
                if (password.equals(employee.getPassword())){
                    session.setAttribute("user",username);
                    sessions.put("admin"+employee.getId(),session);
                    reposeMap.put("code","200");
                    reposeMap.put("user",employee.getUsername());
                    reposeMap.put("id",employee.getId());
                    reposeMap.put("identity","user");
                    reposeMap.put("emp",employee);
                    reposeMap.put("dep",employee.getDepartment());
                    reposeMap.put("pos",employee.getPosite());
                }else {
                    reposeMap.put("code","403");
                }
            }else {
                reposeMap.put("code","403");
            }

        }
        return reposeMap;
//        String token= JwtUntils.sign(username,password);
//        if (token!=null){
//            reposeMap.put("code","200");
//            reposeMap.put("message","success");
//            reposeMap.put("token",token);
//            session.setAttribute("user",username);
//            return reposeMap;
//        }else {
//            reposeMap.put("code","403");
//            reposeMap.put("message","error");
//            return reposeMap;
//        }
    }

    public void setSalary(){
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());
        Calendar empCalendar = Calendar.getInstance();
        Calendar payCalendar = Calendar.getInstance();

        List<Employee> employees = employeeService.getAll();
        for (Employee employee : employees) {
            Salary salary = salaryService.getSalaryByEmp(employee.getId());

            empCalendar.setTime(employee.getSignDate());
//            System.out.println("工资信息*****"+salary);
            // 如果之前发过工资
            if (salary!=null){
//                System.err.println("之前发过工资的名单"+employee.getUsername());
                payCalendar.setTime(salary.getPayDate());
                // 计算当前月份与上次发工资时的月份差
                int month = nowCalendar.get(Calendar.MONTH) - payCalendar.get(Calendar.MONTH);
                //如果上个月没发工资
                if (Math.abs(month) != 1 && Math.abs(month) != 11) {
                    System.out.println("上个月没发过工资");
                    salaryService.addSalary(employee.getId());
                }
                // 如果之前没发过工资
            }else {
                // 是今年之前或今年入职的
                if (empCalendar.get(Calendar.YEAR) <= nowCalendar.get(Calendar.YEAR)) {
                    // 如果是本月之前入职的
                    if (nowCalendar.get(Calendar.MONTH)>empCalendar.get(Calendar.MONTH)) {
                        salaryService.addSalary(employee.getId());
                    }
                }
            }

        }

    }
}
