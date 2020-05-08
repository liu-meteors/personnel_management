package com.meteor.controller;

import com.meteor.pojo.Contract;
import com.meteor.service.ContractService;
import com.meteor.untils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/7 14:44
 * @description：合同Controller
 * @modified By：
 * @version: 0.0.1$
 */
@RestController
public class ContractController {
    @Autowired
    ContractService contractService;
    @GetMapping("getAllContract")
    public List<Contract> getAllContract(){
        return contractService.getAllContract();
    }
    @PutMapping("/updateContract")
    public String updateContract(@RequestBody Contract contract){
        int isSuccess=contractService.updateContractById(contract);
        return ReturnUtils.isSuccess(isSuccess);
    }
}
