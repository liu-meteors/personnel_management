package com.meteor.service.impl;

import com.meteor.mapper.GradeHistoryMapper;
import com.meteor.pojo.Employee;
import com.meteor.pojo.GradeHistory;
import com.meteor.service.EmployeeService;
import com.meteor.service.GradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/25 18:23
 * @description：成绩历史Service
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class GradeHistoryServiceImpl implements GradeHistoryService {

    @Autowired
    GradeHistoryMapper gradeHistoryMapper;
    @Autowired
    EmployeeService employeeService;

    /**
     * @param gradeHistory
     * @Description: 添加记录
     * @Param: * @Param: gradeHistory
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addGradeHistory(GradeHistory gradeHistory) {
        return gradeHistoryMapper.addGradeHistory(gradeHistory);
    }

    /**
     * @Description: 获取所有考核成绩历史
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<GradeHistory> getAllGradeHistory() {
        List<GradeHistory> gradeHistories=gradeHistoryMapper.getAllGradeHistory();
        setEmpName(gradeHistories);
        return gradeHistories;
    }

    /**
     * @Description: 查询上个月的考核成绩
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<GradeHistory> getAllGradeHistoryNow() {
        List<GradeHistory> gradeHistories=gradeHistoryMapper.getAllGradeHistoryNow();
        setEmpName(gradeHistories);
        return gradeHistories;
    }

    /**
     * @param dep
     * @Description: 根据部门查询历史考核成绩
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<GradeHistory> getGradeHistoryByDep(Integer dep) {
        List<GradeHistory> gradeHistoryList=new ArrayList<>();
        List<GradeHistory> gradeHistories=gradeHistoryMapper.getAllGradeHistory();
        gradeHistoryList= setDepEmpName(gradeHistories,dep);
        return gradeHistoryList;
    }

    /**
     * @param dep
     * @Description: 查询上个月部门的历史考核成绩
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<GradeHistory> getGradeHistoryByDepNow(Integer dep) {
        List<GradeHistory> gradeHistoryList=new ArrayList<>();
        List<GradeHistory> gradeHistories=gradeHistoryMapper.getAllGradeHistoryNow();
        gradeHistoryList= setDepEmpName(gradeHistories,dep);
        return gradeHistoryList;
    }


    private List<GradeHistory> setEmpName(List<GradeHistory> gradeHistories){

        for (int i=0;i<gradeHistories.size();i++){
            Employee employee=employeeService.getEmployeeById(gradeHistories.get(i).getEmpId());
            gradeHistories.get(i).setEmpName(employee.getUsername());
            gradeHistories.get(i).setDepartmentName(employee.getDepartmentName());
            gradeHistories.get(i).setEmpNumber(employee.getEmpNumber());
            gradeHistories.get(i).setPositionName(employee.getPositionName());
        }


        return gradeHistories;
    }
    private List<GradeHistory> setDepEmpName(List<GradeHistory> gradeHistories,Integer dep){
        List<GradeHistory> gradeHistoryList=new ArrayList<>();
        for (int i=0;i<gradeHistories.size();i++){
            Employee employee=employeeService.getEmployeeById(gradeHistories.get(i).getEmpId());
            if (dep==employee.getDepartment()){

                gradeHistories.get(i).setEmpName(employee.getUsername());
                gradeHistories.get(i).setDepartmentName(employee.getDepartmentName());
                gradeHistories.get(i).setEmpNumber(employee.getEmpNumber());
                gradeHistories.get(i).setPositionName(employee.getPositionName());
                gradeHistoryList.add(gradeHistories.get(i));
            }

        }


        return gradeHistories;
    }
}
