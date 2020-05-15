package com.meteor.service.impl;

import com.meteor.mapper.ContractHistoryMapper;
import com.meteor.pojo.ContractHistory;
import com.meteor.service.ContractHistoryService;
import com.meteor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/15 12:15
 * @description：合同历史处理类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class ContractHistoryServiceImpl implements ContractHistoryService {
    @Autowired
    ContractHistoryMapper contractHistoryMapper;
    @Autowired
    EmployeeService employeeService;
    /**
     * @param contractHistory
     * @Description: 添加合同历史
     * @Param: * @Param: contractHistory
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addHistory(ContractHistory contractHistory) {
        contractHistory.setSubmitDate(new Date());
        return contractHistoryMapper.addHistory(contractHistory);
    }

    /**
     * @param id
     * @Description: 删除合同历史记录
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteHistoryById(Integer id) {
        return contractHistoryMapper.deleteHistoryById(id);
    }

    /**
     * @param contractHistory
     * @Description: 修改合同历史记录
     * @Param: * @Param: contractHistory
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int updateHistory(ContractHistory contractHistory) {
        return contractHistoryMapper.updateHistory(contractHistory);
    }

    /**
     * @param empId
     * @Description: 获取员工合同历史
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<ContractHistory> getAllHistoryByEmpId(Integer empId) {
        List<ContractHistory> contractHistories=contractHistoryMapper.getAllHistoryByEmpId(empId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        for (int i=0;i<contractHistories.size();i++){
            contractHistories.get(i).setUsername(employeeService.getEmployeeById(contractHistories.get(i).getEmpId()).getUsername());
            contractHistories.get(i).setEmpNumber(employeeService.getEmployeeById(contractHistories.get(i).getEmpId()).getEmpNumber());
            contractHistories.get(i).setSubmitDateStr(simpleDateFormat.format(contractHistories.get(i).getSubmitDate()));

        }
        return contractHistories;
    }

    /**
     * @param id
     * @Description: 根据id查询合同历史
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public ContractHistory getHistoryById(Integer id) {
        return contractHistoryMapper.getHistoryById(id);
    }
}
