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
    /**
            * @Description: 添加面试信息
            * @Param:  * @Param: interview
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    @PostMapping("/addInterview")
    public String addInterview(@RequestBody Interview interview){
        System.out.println(interview);
        int isSuccess=interviewService.addInterview(interview);
        return ReturnUtils.isSuccess(isSuccess);

    }
    /**
     * @Description: 获取当天面试信息
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllInterviewNow")
    public List<Interview> getAllInterviewNow(){
        return interviewService.getAllInterviewNow();
    }
    /**
            * @Description: 获取所有面试信息
            * @Param:  * @Param:
            * @return:
            * @Author: liujingyu
            * @Date:
            */
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
    /**
            * @Description: 发送入职通知书
            * @Param:  * @Param: fileName
 * @Param: id
 * @Param: request
            * @return:
            * @Author: liujingyu
            * @Date:
            */
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


    /**
     * @Description: 获取该部门当天的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllInterviewNowByDep/{dep}")
  public   List<Interview> getAllInterviewNowByDep(@PathVariable("dep") Integer dep){
        return interviewService.getAllInterviewNowByDep(dep);
    }

    /**
     * @Description: 获取该部门当所有的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @GetMapping("/getAllInterviewByDep/{dep}")
    public List<Interview> getAllInterviewByDep(@PathVariable("dep") Integer dep){
        return interviewService.getAllInterviewByDep(dep);
    }

}
