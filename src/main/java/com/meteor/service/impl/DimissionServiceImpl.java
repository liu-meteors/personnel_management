package com.meteor.service.impl;

import com.meteor.mapper.DepartmentMapper;
import com.meteor.mapper.DimissionMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Department;
import com.meteor.pojo.Dimission;
import com.meteor.pojo.Position;
import com.meteor.service.DimissionService;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/8 0:15
 * @description：DimissionService接口的实现类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class DimissionServiceImpl implements DimissionService {
    @Autowired
    DimissionMapper dimissionMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    PositionMapper positionMapper;
    @Override
    public List<Dimission> getAllDimission() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Dimission> dimissions=dimissionMapper.getAllDimission();
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        for (int i=0;i<dimissions.size();i++){
            for (int j=0;j<departments.size();j++){
                if (dimissions.get(i).getDepartment()==departments.get(j).getId()){
                    dimissions.get(i).setDepartmentName(departments.get(j).getName());
                    break;
                }
            }
            for (int k=0;k<positions.size();k++){
                if (dimissions.get(i).getPosition()==positions.get(k).getId()){
                    dimissions.get(i).setPositionName(positions.get(k).getName());
                    break;
                }
            }
            dimissions.get(i).setLeaveDateStr(simpleDateFormat.format(dimissions.get(i).getLeaveDate()));
            if (dimissions.get(i).getIsOver()==0){
                dimissions.get(i).setIsOverStr("未完成");
                dimissions.get(i).setDelivery(false);
            }else {
                dimissions.get(i).setIsOverStr("已完成");
                dimissions.get(i).setDelivery(true);
            }
        }
        System.out.println(dimissions);
        return dimissions;
    }

    @Override
    public Dimission getDimissionById(Integer id) {
        Dimission dimission=dimissionMapper.getDimissionById(id);
        List<Department> departments=departmentMapper.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for (int j=0;j<departments.size();j++){
            if (dimission.getDepartment()==departments.get(j).getId()){
                dimission.setDepartmentName(departments.get(j).getName());
                break;
            }
        }
        for (int k=0;k<positions.size();k++){
            if (dimission.getPosition()==positions.get(k).getId()){
                dimission.setPositionName(positions.get(k).getName());
                break;
            }
        }
        dimission.setLeaveDateStr(simpleDateFormat.format(dimission.getLeaveDate()));
        if (dimission.getIsOver()==0){
            dimission.setIsOverStr("未完成");
            dimission.setDelivery(false);
        }else {
            dimission.setIsOverStr("已完成");
            dimission.setDelivery(true);
        }
        return dimission;
    }

    @Override
    public Integer updateDimission(Dimission dimission) {
        System.out.println(dimission.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dimission.setLeaveDate(DateUtils.getTrueDate(simpleDateFormat.parse(dimission.getLeaveDateStr().split("T")[0])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dimission.isDelivery()){
            dimission.setIsOver(1);
        }else {
            dimission.setIsOver(0);
        }
        System.out.println(dimission.toString());
        return dimissionMapper.updateDimission(dimission);
    }

    @Override
    public Integer deleteDimission(Integer id) {
        return dimissionMapper.deleteDimission(id);
    }

    @Override
    public Integer addDimission(Dimission dimission) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dimission.setLeaveDate(DateUtils.getTrueDate(simpleDateFormat.parse(dimission.getLeaveDateStr().split("T")[0])));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dimission.isDelivery()){
            dimission.setIsOver(1);
        }else {
            dimission.setIsOver(0);
        }
        return dimissionMapper.addDimission(dimission);
    }
}
