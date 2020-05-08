package com.meteor.service.impl;

import com.meteor.mapper.AwardMapper;
import com.meteor.mapper.EmployeeMapper;
import com.meteor.pojo.Award;
import com.meteor.pojo.Employee;
import com.meteor.service.AwardService;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/14 17:15
 * @description：奖惩信息的service
 * @modified By：
 * @version: 0.0.1$
 */
@Service
class AwardServiceImpl implements AwardService {
    @Autowired
    AwardMapper awardMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    /**
     * @Description: 获取所有奖惩信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Award> getAllAward() {
        List<Award> awards=awardMapper.getAllAward();
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<awards.size();i++){
            for (int j=0;j<employees.size();j++){
               Award award= setAwardInformation(awards.get(i),employees.get(j));
               if (award!=null){
                   awards.set(i,award);
               }
            }
            awards.get(i).setRecordDateStr(simpleDateFormat.format(awards.get(i).getRecordDate()));
        }
        return awards;
    }

    /**
     * @param id
     * @Description: 根据id查询奖惩信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public Award getAwardById(Integer id) {
        Award award=awardMapper.getAwardById(id);
        Employee employee=employeeMapper.getEmployeeById(award.getEmpId());
        award=setAwardInformation(award,employee);
        award.setRecordDateStr(simpleDateFormat.format(award.getRecordDate()));
        return award;
    }

    /**
     * @param empId
     * @Description: 根据员工查询奖惩信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Award> getAwardByEmpId(Integer empId) {
        List<Award> awards=awardMapper.getAwardByEmpId(empId);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<awards.size();i++){
            for (int j=0;j<employees.size();j++){
                Award award= setAwardInformation(awards.get(i),employees.get(j));
                if (award!=null){
                    awards.set(i,award);
                }
            }
            awards.get(i).setRecordDateStr(simpleDateFormat.format(awards.get(i).getRecordDate()));
        }
        System.out.println("员工端奖惩"+awards);
        return awards;
    }

    /**
     * @param type
     * @Description: 根据类型查询奖惩信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Award> getAwardByType(Integer type) {
        return null;
    }

    /**
     * @param award
     * @Description: 修改
     * @Param: * @Param: award
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int updateAward(Award award) {
        try {
            award.setRecordDate(DateUtils.getTrueDate(simpleDateFormat.parse(award.getRecordDateStr().split("T")[0])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("添加奖惩"+award.toString());
        return awardMapper.updateAward(award);
    }

    /**
     * @param id
     * @Description: 根据id删除信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteAwardById(Integer id) {
        System.out.println(id);
        return awardMapper.deleteAwardById(id);
    }

    /**
     * @param id
     * @Description: 根据员工id删除信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteAwardByEmpId(Integer id) {
        return awardMapper.deleteAwardByEmpId(id);
    }

    /**
     * @param award
     * @Description: 添加信息
     * @Param: * @Param: award
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addAward(Award award) {
        try {
            award.setRecordDate(DateUtils.getTrueDate(simpleDateFormat.parse(award.getRecordDateStr().split("T")[0])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("添加奖惩"+award.toString());
        return awardMapper.addAward(award);
    }

    @Override
    public List<Award> getAllAwardByEmpNow(Integer empId) {
        return awardMapper.getAllAwardByEmpNow(empId);
    }

    public Award setAwardInformation(Award award,Employee employee){
        if (award.getEmpId()==employee.getId()){
            award.setEmpName(employee.getUsername());
            if (award.getAwardType()==1){
                award.setTypeName("奖励");
            }else {
                award.setTypeName("惩罚");
            }
            return award;
        }else {
            return null;
        }

    }

}
