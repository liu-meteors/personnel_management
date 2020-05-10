package com.meteor.controller;

import com.meteor.pojo.Promotion;
import com.meteor.service.PromotionService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/14 14:52
 * @description：职位变更的Controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class PromotionController {
    @Autowired
    PromotionService promotionService;
    @GetMapping("/getAllPromotion")
    public List<Promotion> getAllPromotion(){
        return promotionService.getAllPromotion();
    }
    @GetMapping("/getAllPromotionByEmpId/{empId}")
    public List<Promotion> getAllPromotionByEmpId(@PathVariable("empId") Integer empId){
        return promotionService.getPromotionByEmpId(empId);
    }
    @DeleteMapping("/deletePromotion/{id}")
    public String deletePromotion(@PathVariable("id") Integer id){
        int isSuccess=promotionService.deletePromotion(id);
        return ReturnUtils.isSuccess(isSuccess);
    }

    @GetMapping("/getPromotionByEmpIdYear/{id}")
    public List<Promotion> getPromotionByEmpIdYear(@PathVariable("id") Integer empId){
        return promotionService.getPromotionByEmpIdYear(empId);
    }
}
