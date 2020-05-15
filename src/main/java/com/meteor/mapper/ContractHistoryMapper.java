package com.meteor.mapper;

import com.meteor.pojo.ContractHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/15 11:49
 * @description：合同历史Mapper
 * @modified By：
 * @version: 0.0.1$
 */
@Mapper
@Repository
public interface ContractHistoryMapper {
    int addHistory(ContractHistory contractHistory);
    int deleteHistoryById(Integer id);
    int updateHistory(ContractHistory contractHistory);
    List<ContractHistory> getAllHistoryByEmpId(Integer empId);
    ContractHistory getHistoryById(Integer id);


}
