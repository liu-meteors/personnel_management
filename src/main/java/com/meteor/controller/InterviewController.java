package com.meteor.controller;

import com.meteor.pojo.Interview;
import com.meteor.service.InterviewService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
    @GetMapping("/sendOffer/{fileName}/{id}")
    public String sendOffer(@PathVariable("fileName") String fileName, @PathVariable("id") Integer id, HttpServletRequest request){
        String realPath = request.getServletContext().getRealPath("/upload") + "/mail/";
        Interview interview=interviewService.getInterviewById(id);
        String name=interview.getName();
        String mail=interview.getMail();
        String content=name+":恭喜您通过了本公司的面试，附件为您的录用Offer";
        System.out.println(realPath+fileName);
        String filePath=realPath+fileName;
        int isSuccess=0;
        File file=new File(realPath+fileName);
        if (file.exists()){
            System.out.println("存在");
            isSuccess =interviewService.sendSimpleTextMailActual("Offer",content,new String[]{mail},null,null,new String[]{filePath});
            file.delete();
        }else {
            System.out.println("不存在");
            isSuccess=0;
        }
        return ReturnUtils.isSuccess(isSuccess);
    }
}
