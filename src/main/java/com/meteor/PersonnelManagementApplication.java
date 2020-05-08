package com.meteor;

import com.meteor.pojo.ManagerSessions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.meteor.mapper")
public class PersonnelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelManagementApplication.class, args);
    }

}
