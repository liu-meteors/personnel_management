package com.meteor;

import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Contract;
import com.meteor.pojo.Department;
import com.meteor.pojo.Position;
import com.meteor.service.BenefitService;
import com.meteor.service.ContractService;
import com.meteor.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class PersonnelManagementApplicationTests {

    @Autowired
    ContractService contractService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionMapper positionMapper;


    @Test
    void contextLoads() throws Exception {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        for (int i=0;i<departments.size();i++){
            for (int j=0;j<positions.size();j++){
                Contract contract=new Contract();
                contract.setDepartment(departments.get(i).getId());
                contract.setPosite(positions.get(j).getId());
                contractService.addContract(contract);
            }
        }
    }


}
