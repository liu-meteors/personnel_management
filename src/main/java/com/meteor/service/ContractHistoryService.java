package com.meteor.service;

import com.meteor.pojo.ContractHistory;

import java.util.List;

public interface ContractHistoryService {
    /**
            * @Description: 添加合同历史
            * @Param:  * @Param: contractHistory
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int addHistory(ContractHistory contractHistory);
    /**
            * @Description: 删除合同历史记录
            * @Param:  * @Param: id
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int deleteHistoryById(Integer id);
    /**
            * @Description: 修改合同历史记录
            * @Param:  * @Param: contractHistory
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int updateHistory(ContractHistory contractHistory);
    /**
            * @Description: 获取员工合同历史
            * @Param:  * @Param: empId
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    List<ContractHistory> getAllHistoryByEmpId(Integer empId);
    /**
            * @Description: 根据id查询合同历史
            * @Param:  * @Param: id
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    ContractHistory getHistoryById(Integer id);
}

