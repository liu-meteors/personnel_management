package com.meteor.service;

import com.meteor.pojo.Contract;

import java.util.List;

public interface ContractService {
    int addContract(Contract contract);
    int deleteContractById(Integer id);
    int updateContractById(Contract contract);
    List<Contract> getAllContract();
    Contract getContractById(Integer id);
}
