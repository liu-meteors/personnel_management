package com.meteor.mapper;

import com.meteor.pojo.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContractMapper {
     int addContract(Contract contract);
     int deleteContractById(Integer id);
     int updateContractById(Contract contract);
     List<Contract> getAllContract();
     Contract getContractById(Integer id);
}
