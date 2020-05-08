package com.meteor.controller;

import com.meteor.pojo.Interview;
import com.meteor.service.InterviewService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/6 16:14
 * @description：面试的Controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @PostMapping("/addInterview")
    public String addInterview(@RequestBody Interview interview){
        System.out.println(interview);
        int isSuccess=interviewService.addInterview(interview);
        return ReturnUtils.isSuccess(isSuccess);

    }
    @GetMapping("/getAllInterviewNow")
    public List<Interview> getAllInterviewNow(){
        return interviewService.getAllInterviewNow();
    }
    @GetMapping("/getAllInterview")
    public List<Interview> getAllInterview(){
        return interviewService.getAllInterview();
    }

    @PutMapping("/updateInterviewById")
    public String updateInterviewById(@RequestBody Interview interview){
        System.out.println(interview);
        int isSuccess=interviewService.updateInterviewById(interview);
        return ReturnUtils.isSuccess(isSuccess);
    }
    @DeleteMapping("/deleteInterviewById/{id}")
    public String deleteInterviewById(@PathVariable("id") Integer id){
        int isSuccess=interviewService.deleteInterviewById(id);
        return ReturnUtils.isSuccess(isSuccess);
    }
}
