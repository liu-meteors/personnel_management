package com.meteor.controller;

import com.meteor.pojo.ContractHistory;
import com.meteor.service.ContractHistoryService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/15 12:21
 * @description：合同类的Controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class ContractHistoryController {
    @Autowired
    ContractHistoryService contractHistoryService;
@GetMapping("/getAllHistoryByEmpId/{id}")
public List<ContractHistory> getAllHistoryByEmpId(@PathVariable("id") Integer id){
    return contractHistoryService.getAllHistoryByEmpId(id);
}
@GetMapping("/getDownHistoryByEmpId/{id}")
public ContractHistory getDownHistoryByEmpId(@PathVariable("id") Integer id){
    List<ContractHistory> contractHistories=contractHistoryService.getAllHistoryByEmpId(id);
    if (contractHistories.size()==0){
        return null;
    }
    return contractHistories.get(0);
}
@PostMapping("/addHistory")
public String addHistory(@RequestBody ContractHistory contractHistory){
    int isSuccess=contractHistoryService.addHistory(contractHistory);
    return ReturnUtils.isSuccess(isSuccess);
}

@DeleteMapping("/deleteHistory/{id}")
public String deleteHistory(@PathVariable("id") Integer id,HttpServletRequest request){
    ContractHistory contractHistory=contractHistoryService.getHistoryById(id);
    File file=new File(contractHistory.getFileAddress());
    if (file.exists()){
        System.out.println("存在");

        file.delete();
    }else {
        System.out.println("不存在");
    }
    int isSuccess=contractHistoryService.deleteHistoryById(id);
    return ReturnUtils.isSuccess(isSuccess);
}

}
