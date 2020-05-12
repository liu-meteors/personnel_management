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
    InterviewService interviewService;

    @Test
    void contextLoads() throws Exception {
        String mail="1286717282@qq.com";
        String filePath="D:\\IdeaProjects\\personnel_management\\src\\main\\resources\\InterviewFile\\desc.txt";
        String content="jjjjjjjjjjjfiohfioewhfuiewhfuweuifhweiufhweuihfiweuhfuiewhuiwehfhuiweehfuiwbvbrhbvhrjbgjghegierngkerjg";
        interviewService.sendSimpleTextMailActual("测试",content,new String[]{mail},null,null,new String[]{filePath});
    }


}
