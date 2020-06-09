package com.meteor.service.impl;

import com.meteor.mapper.EmployeeMapper;
import com.meteor.mapper.LeaveMapper;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Leave;
import com.meteor.service.LeaveService;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/23 15:11
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    LeaveMapper leaveMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Leave> getAllLeave() {
        List<Leave> leaves=leaveMapper.getAllLeave();
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        return leaves;
    }

    @Override
    public List<Leave> getAllDepApprove() {
        List<Leave> leaves=leaveMapper.getAllDepApprove();
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        return leaves;
    }

    @Override
    public List<Leave> getAllApprove() {
        List<Leave> leaves=leaveMapper.getAllApprove();
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        return leaves;
    }

    @Override
    public List<Leave> getAllByEmpId(Integer id) {

        List<Leave> leaves=leaveMapper.getAllByEmpId(id);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        System.out.println("个人全部"+leaves);
        return leaves;
    }

    @Override
    public int updateLeave(Leave leave) {

        return leaveMapper.updateLeave(leave);
    }

    @Override
    public int addLeave(Leave leave) {
        leave.setStartDate(DateUtils.dealDateFormat(leave.getStartToOver()[0]));
        leave.setOverDate(DateUtils.dealDateFormat(leave.getStartToOver()[1]));
        leave.setApplyDate(new Date());
        System.out.println(leave);
        leave.setDepApprove(0);
        leave.setHrApprove(0);
        return leaveMapper.addLeave(leave);
//        return 1;
    }

    @Override
    public Leave getLeaveById(Integer id) {
        Leave leave=leaveMapper.getLeaveById(id);
        List<Employee> employees=employeeMapper.getAll();
        leave=setEmpName(leave,employees);
        System.out.println(leave);
        return leave;
    }

    @Override
    public List<Leave> getAllLeaveNow(Integer id) {
        List<Leave> leaves=leaveMapper.getAllLeaveNow(id);
        System.out.println("当月：：：："+leaves);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        return leaves;
    }

    @Override
    public List<Leave> getAllLeaveByNow() {
        List<Leave> leaves=leaveMapper.getAllLeaveByNow();
        System.out.println("当月：：：："+leaves);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employees));
        }
        return leaves;
    }

    /**
     * @param dep
     * @Description: 获取部门所有请假信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Leave> getAllDepLeave(Integer dep) {
        List<Employee> employeeList=employeeMapper.getEmpByDep(dep);
        List<Leave> leaves=leaveMapper.getAllLeave();
        leaves=getDepLeave(leaves,employeeList);
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employeeList));
        }
        return leaves;
    }

    /**
     * @param dep
     * @Description: 获取部门当月所有请假信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Leave> getLeaveByDepMonth(Integer dep) {
        List<Employee> employeeList=employeeMapper.getEmpByDep(dep);
        List<Leave> leaves=leaveMapper.getAllLeaveByNow();
        leaves=getDepLeave(leaves,employeeList);
        for (int i=0;i<leaves.size();i++){
            leaves.set(i,setEmpName(leaves.get(i),employeeList));
        }
        return leaves;
    }

    @Override
    public int deleteLeaveById(Integer id) {
        return leaveMapper.deleteLeaveById(id);
    }

    public List<Leave> getDepLeave(List<Leave> leaves,List<Employee> employees){
        List<Leave> leaveList=new ArrayList<>();
        for (Leave leave:leaves){
            for (Employee employee:employees){
                if (leave.getEmpId()==employee.getId()){
                    leaveList.add(leave);
                    break;
                }
            }
        }
        return leaveList;
    }

    public Leave setEmpName(Leave leave, List<Employee> employees){
        for (int i=0;i<employees.size();i++){
            if (leave.getEmpId()==employees.get(i).getId()){
                leave.setEnpName(employees.get(i).getUsername());
                leave.setEmpNumber(employees.get(i).getEmpNumber());
                break;
            }
        }
        if (leave.getHrApprove()==0){

            if (leave.getStartDate().before(new Date())){
                System.out.println(leave.getId()+"ddd"+leave.getStartDate());
                leave.setHrApprove(3);
                updateLeave(leave);
            }
        }
//        if (leave.getDepApprove()==0||leave.getHrApprove()==0){
//            if ()
//        }
        if (leave.getDepApprove()==0){
            leave.setIsCheck("未审批");
        }else if (leave.getDepApprove()==1){
            leave.setIsCheck("已拒绝");
        }else if (leave.getDepApprove()==2){
            leave.setIsCheck("审批中");
        }
       if (leave.getHrApprove()==1){
            leave.setIsCheck("已拒绝");
        }else if (leave.getHrApprove()==2){
            leave.setIsCheck("已批准");
        }else if (leave.getHrApprove()==3){
           leave.setIsCheck("已超时");
       }
        leave.setApplyDateStr(simpleDateFormat.format(leave.getApplyDate()));
        leave.setStartDateStr(simple.format(leave.getStartDate()));
        leave.setOverDateStr(simple.format(leave.getOverDate()));
        String[] strings={leave.getStartDateStr(),leave.getOverDateStr()};
        leave.setStartToOver(strings);
        return leave;
    }
}
