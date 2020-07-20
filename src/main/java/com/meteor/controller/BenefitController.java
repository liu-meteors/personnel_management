package com.meteor.controller;

import com.meteor.pojo.Benefit;
import com.meteor.service.BenefitService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/4 17:02
 * @description：效益controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class BenefitController {
    @Autowired
    BenefitService benefitService;

    @GetMapping("/getAllBenefitByDepYear/{dep}")
    public List<Benefit> getAllBenefitByDepYear(@PathVariable("dep") Integer dep){
        return benefitService.getAllBenefitByDepYear(dep);
    }
    @GetMapping("/getAllBenefitByDep/{dep}")
    public List<Benefit> getAllBenefitByDep(@PathVariable("dep") Integer dep){
        return benefitService.getAllBenefitByDep(dep);
    }
    @GetMapping("/getAllBenefitByYear")
    public List<Benefit> getAllBenefitByYear(){
        return benefitService.getAllBenefitByYear();
    }
    @GetMapping("/getAllBenefit")
    public List<Benefit> getAllBenefit(){
        return benefitService.getAllBen();
    }


    @PostMapping("/addBenefit")
    public String addBenefit(@RequestBody Benefit benefit){
        System.out.println("添加：："+benefit.toString());
        int isSuccess=benefitService.addBen(benefit);
        return ReturnUtils.isSuccess(isSuccess);
    }
    @PutMapping("/updateBenefit")
    public String updateBenefit(@RequestBody Benefit benefit){
        System.out.println("修改"+benefit);
        int isSuccess=benefitService.updateBen(benefit);
        return ReturnUtils.isSuccess(isSuccess);
    }

}
