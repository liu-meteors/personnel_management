package com.meteor.controller;

import com.meteor.pojo.YearBenefit;
import com.meteor.service.YearBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/21 10:32
 * @description：年效益的交互层
 * @modified By：
 * @version: $
 */
@RestController
public class YearBenefitController {
    @Autowired
    YearBenefitService yearBenefitService;
    /**
            * @Description: 获取所有年效益信息
            * @Param:  * @Param:
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    @GetMapping("/getAllYearBenefit")
    public List<YearBenefit> getAllYearBenefit(){
        return yearBenefitService.getAllYearBenefit();
    }
    @GetMapping("/getDepYearBenefit/{dep}")
    public List<YearBenefit> getDepYearBenefit(@PathVariable("dep") Integer dep){
        return yearBenefitService.getDepYearBenefit(dep);
    }
}
