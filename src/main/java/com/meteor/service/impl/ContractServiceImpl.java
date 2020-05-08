package com.meteor.service.impl;

import com.meteor.mapper.ContractMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Contract;
import com.meteor.pojo.Department;
import com.meteor.pojo.Position;
import com.meteor.service.ContractService;
import com.meteor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/7 14:02
 * @description：合同逻辑处理类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractMapper contractMapper;
    @Autowired
    DepartmentService departmentService;

    @Autowired
    PositionMapper positionMapper;

    @Override
    public int addContract(Contract contract) {
        return contractMapper.addContract(contract);
    }

    @Override
    public int deleteContractById(Integer id) {
        return deleteContractById(id);
    }

    @Override
    public int updateContractById(Contract contract) {
        return updateContractById(contract);
    }

    @Override
    public List<Contract> getAllContract() {
        List<Contract> contracts=contractMapper.getAllContract();
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        for (int i=0;i<contracts.size();i++){
            contracts.set(i,setContract(contracts.get(i),departments,positions));
        }
        return contracts;
    }

    @Override
    public Contract getContractById(Integer id) {
        Contract contract=contractMapper.getContractById(id);
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        contract=setContract(contract,departments,positions);
        return contract;
    }

    public Contract setContract(Contract contract, List<Department> departments, List<Position> positions){
        for (int i=0;i<departments.size();i++){
            if (departments.get(i).getId()==contract.getDepartment()){
                contract.setDepartmentName(departments.get(i).getName());
                break;
            }
        }
        for (int i=0;i<positions.size();i++){
            if (positions.get(i).getId()==contract.getPosite()){
                contract.setPositionName(positions.get(i).getName());
                break;
            }
        }
        return contract;
    }

}
