package com.meteor;

import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.*;
import com.meteor.service.*;
import com.meteor.untils.AESOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonnelManagementApplicationTests {

    @Autowired
    ContractService contractService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AdminService adminService;

    @Test
    void contextLoads() throws Exception {
       List<Employee> employees=employeeService.getAll();
       for (Employee employee:employees){
           System.out.println(AESOperator.decrypt(employee.getPassword()));
       }
    }


}
