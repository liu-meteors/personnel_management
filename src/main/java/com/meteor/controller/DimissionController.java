package com.meteor.controller;

import com.meteor.pojo.Dimission;
import com.meteor.service.DimissionService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/8 0:32
 * @description：离职登记的controller层
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class DimissionController {
    @Autowired
    DimissionService dimissionService;
    /** 
            * @Description: 获取所有离职信息 
            * @Param:  * @Param: 
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @GetMapping("/getAllDimission")
    public List<Dimission> getAllDimission(){
        return dimissionService.getAllDimission();
    }

    /** 
            * @Description: 添加离职信息 
            * @Param:  * @Param: dimission
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PostMapping("/addDimission")
    public String addDimission(@RequestBody Dimission dimission){
        int isSuccess=dimissionService.addDimission(dimission);
        return ReturnUtils.isSuccess(isSuccess);
    }
    /** 
            * @Description: 删除离职信息 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @DeleteMapping("/deleteDimissionById/{id}")
    public String deleteDimissionById(@PathVariable("id") Integer id){
        int isSuccess=dimissionService.deleteDimission(id);
        return ReturnUtils.isSuccess(isSuccess);
    }
    /** 
            * @Description: 修改离职信息 
            * @Param:  * @Param: dimission
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    @PutMapping("/updateDimission")
    public String updateDimission(@RequestBody Dimission dimission){
        int isSuccess=dimissionService.updateDimission(dimission);
        return ReturnUtils.isSuccess(isSuccess);
    }


    @GetMapping("/getDimissionById/{id}")
    public Dimission getDimissionById(@PathVariable("id") Integer id){
        return dimissionService.getDimissionById(id);
    }
}
