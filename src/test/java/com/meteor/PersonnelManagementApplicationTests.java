package com.meteor;

import com.meteor.mapper.BenefitMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.*;
import com.meteor.service.*;
import com.meteor.untils.AESOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
    @Autowired
    BenefitMapper benefitMapper;
    @Autowired
    BenefitService benefitService;

    @Test
    void contextLoads() throws Exception {
        Benefit benefit=new Benefit();
        benefit.setMoney(100);
        benefit.setFillInDate(new Date());
        System.out.println(benefitService.updateBen(benefit));
    }


}
