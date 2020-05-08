package com.meteor.controller;

import com.meteor.pojo.Recruit;
import com.meteor.service.RecruitService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/7 21:08
 * @description：招聘的Controller层
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class RecruitController {
    @Autowired
    RecruitService recruitService;
    @GetMapping("/getAllRecruit")
    public List<Recruit> getAllRecruit(){
        return recruitService.getAllRecruit();
    }
    @PostMapping("/addRecruit")
    public String addRecruit(@RequestBody Recruit recruit){
        int isSuccess=recruitService.addRecruit(recruit);
        return ReturnUtils.isSuccess(isSuccess);

    }
    @GetMapping("/deleteRecruit/{id}")
    public String deleteRecruit(@PathVariable("id") Integer id){
        int isSuccess=recruitService.deleteRecruitById(id);
        return ReturnUtils.isSuccess(isSuccess);
    }
    @PutMapping("/updateRecruit")
    public String updateRecruit(@RequestBody Recruit recruit){
        int isSuccess=recruitService.updateRecruit(recruit);
        return ReturnUtils.isSuccess(isSuccess);
    }
    @GetMapping("/getRecruitById/{id}")
    public Recruit getRecruitById(@PathVariable("id")Integer id){
        return recruitService.getRecruitById(id);
    }

}
