package com.meteor.service.impl;

import com.meteor.mapper.DepartmentMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.mapper.RecruitMapper;
import com.meteor.pojo.Department;
import com.meteor.pojo.Position;
import com.meteor.pojo.Recruit;
import com.meteor.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/7 17:49
 * @description：招聘service实现类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    RecruitMapper recruitMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;

    @Override
    public Integer addRecruit(Recruit recruit) {

        return recruitMapper.addRecruit(recruit);
    }

    @Override
    public List<Recruit> getAllRecruit() {
        List<Recruit> recruits=recruitMapper.getAllRecruit();
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        for (int i=0;i<recruits.size();i++){
            for (int j=0;j<departments.size();j++){
                if (recruits.get(i).getDepartment()==departments.get(j).getId()){
                    recruits.get(i).setDepartmentName(departments.get(j).getName());
                    break;
                }
            }
            for (int k=0;k<positions.size();k++){
                if (recruits.get(i).getPosition()==positions.get(k).getId()){
                    recruits.get(i).setPositionName(positions.get(k).getName());
                    break;
                }
            }
        }
        System.out.println(recruits);
        return recruits;
    }

    @Override
    public Integer deleteRecruitById(Integer id) {
        return recruitMapper.deleteRecruitById(id);
    }

    @Override
    public Recruit getRecruitById(Integer id) {
        Recruit recruit = recruitMapper.getRecruitById(id);
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        for (int j=0;j<departments.size();j++){
            if (recruit.getDepartment()==departments.get(j).getId()){
                recruit.setDepartmentName(departments.get(j).getName());
                break;
            }
        }
        for (int k=0;k<positions.size();k++){
            if (recruit.getPosition()==positions.get(k).getId()){
                recruit.setPositionName(positions.get(k).getName());
                break;
            }
        }
        return recruit;
    }

    @Override
    public Integer updateRecruit(Recruit recruit) {
        return recruitMapper.updateRecruit(recruit);
    }
}
