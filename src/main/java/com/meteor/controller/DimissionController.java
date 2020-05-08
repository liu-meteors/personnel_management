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

    @GetMapping("/getAllDimission")
    public List<Dimission> getAllDimission(){
        return dimissionService.getAllDimission();
    }


    @PostMapping("/addDimission")
    public String addDimission(@RequestBody Dimission dimission){
        int isSuccess=dimissionService.addDimission(dimission);
        return ReturnUtils.isSuccess(isSuccess);
    }

    @DeleteMapping("/deleteDimissionById/{id}")
    public String deleteDimissionById(@PathVariable("id") Integer id){
        int isSuccess=dimissionService.deleteDimission(id);
        return ReturnUtils.isSuccess(isSuccess);
    }

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
