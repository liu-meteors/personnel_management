package com.meteor.controller;

import com.meteor.pojo.Award;
import com.meteor.service.AwardService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/14 23:24
 * @description：奖惩信息的controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class AwardController {
    @Autowired
    AwardService awardService;

    @GetMapping("/getAllAward")
    public List<Award> getAllAward(){
        return awardService.getAllAward();
    }
    @GetMapping("/getAwardById/{id}")
    public Award getAwardById(@PathVariable("id") Integer id){
        return awardService.getAwardById(id);
    }
    @GetMapping("/getAllAwardByEmpId/{empId}")
    public List<Award> getAllAwardByEmpId(@PathVariable("empId") Integer id){
        return awardService.getAwardByEmpId(id);
    }
    @DeleteMapping("/deleteAwardById/{id}")
    public String deleteAwardById(@PathVariable("id") Integer id){
        int isSuccess=awardService.deleteAwardById(id);
        return ReturnUtils.isSuccess(isSuccess);
    }

    @PostMapping("/addAward")
    public String addAward(@RequestBody Award award){
        int isSuccess=awardService.addAward(award);
        return  ReturnUtils.isSuccess(isSuccess);
    }
    @PutMapping("/updateAward")
    public String updateAward(@RequestBody Award award){
        System.out.println(award);
        int isSuccess=awardService.updateAward(award);
        return  ReturnUtils.isSuccess(isSuccess);
    }
    @GetMapping("/getAwardByEmpIdMonth/{empId}")
    public List<Award> getAwardByEmpIdMonth(@PathVariable("empId") Integer empId){
            List<Award> awards= awardService.getAwardByEmpId(empId);
            List<Award> mouthAwards=new ArrayList<>();
        Calendar calendar=Calendar.getInstance();
        Calendar nowCalendar=Calendar.getInstance();
            Date nowDate=new Date();
        nowCalendar.setTime(nowDate);
            for (int i=0;i<awards.size();i++){
                calendar.setTime(awards.get(i).getRecordDate());
                boolean year=calendar.get(Calendar.YEAR)==nowCalendar.get(Calendar.YEAR);
                boolean mouth=calendar.get(Calendar.MONTH)==nowCalendar.get(Calendar.MONTH);
                if (year&&mouth){
                    mouthAwards.add(awards.get(i));
                }
            }
        System.out.println(mouthAwards);
            return mouthAwards;
    }


    @GetMapping("/getAllAwardByEmpYear/{id}")
    public List<Award> getAllAwardByEmpYear(@PathVariable("id") Integer empId){
        return awardService.getAllAwardByEmpYear(empId);
    }

}
