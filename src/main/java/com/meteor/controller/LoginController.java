package com.meteor.controller;

import com.meteor.pojo.Admin;
import com.meteor.pojo.Employee;
import com.meteor.pojo.ManagerSessions;
import com.meteor.service.AdminService;
import com.meteor.service.EmployeeService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
@RestController
@CrossOrigin(allowCredentials = "true")
public class LoginController {
    private Map<String, HttpSession> sessions=new HashMap<String,HttpSession>();

    @Autowired
    AdminService adminService;
    @Autowired
    EmployeeService employeeService;


    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,Object> map,HttpSession session)  {
        Map<String,Object> reposeMap=new HashMap<>();
        String username= (String) map.get("username");
        String password= (String) map.get("password");
        String identity= (String) map.get("identity");

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
                    reposeMap.put("user",username);
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
}
