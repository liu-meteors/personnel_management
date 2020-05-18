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
    /** 
            * @Description: 获取所有招聘信息 
            * @Param:  * @Param: 
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @GetMapping("/getAllRecruit")
    public List<Recruit> getAllRecruit(){
        return recruitService.getAllRecruit();
    }
    /** 
            * @Description: 添加招聘信息 
            * @Param:  * @Param: recruit
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PostMapping("/addRecruit")
    public String addRecruit(@RequestBody Recruit recruit){
        int isSuccess=recruitService.addRecruit(recruit);
        return ReturnUtils.isSuccess(isSuccess);

    }
    /** 
            * @Description: 删除招聘信息 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @GetMapping("/deleteRecruit/{id}")
    public String deleteRecruit(@PathVariable("id") Integer id){
        int isSuccess=recruitService.deleteRecruitById(id);
        return ReturnUtils.isSuccess(isSuccess);
    }
    /** 
            * @Description: 修改招聘信息 
            * @Param:  * @Param: recruit
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PutMapping("/updateRecruit")
    public String updateRecruit(@RequestBody Recruit recruit){
        int isSuccess=recruitService.updateRecruit(recruit);
        return ReturnUtils.isSuccess(isSuccess);
    }
    /** 
            * @Description: 获取某一个招聘的信息 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @GetMapping("/getRecruitById/{id}")
    public Recruit getRecruitById(@PathVariable("id")Integer id){
        return recruitService.getRecruitById(id);
    }

}
