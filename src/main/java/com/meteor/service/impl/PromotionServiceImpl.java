package com.meteor.service.impl;

import com.meteor.mapper.EmployeeMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.mapper.PromotionMapper;
import com.meteor.pojo.Employee;
import com.meteor.pojo.Position;
import com.meteor.pojo.Promotion;
import com.meteor.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/13 18:17
 * @description：晋升信息
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionMapper promotionMapper;
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public List<Promotion> getAllPromotion() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Promotion> promotions= promotionMapper.getAllPromotion();
        List<Position> positions=positionMapper.getAllPosition();
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<promotions.size();i++){
            for (int j=0;j<positions.size();j++){
                if (promotions.get(i).getOldPosition()==positions.get(j).getId()){
                    promotions.get(i).setOldPositionName(positions.get(j).getName());
                }
                if (promotions.get(i).getNewPosition()==positions.get(j).getId()){
                    promotions.get(i).setNewPositionName(positions.get(j).getName());
                }
            }
            for (int k=0;k<employees.size();k++){
                if (employees.get(k).getId()==promotions.get(i).getEmpId()){
                    promotions.get(i).setEnpName(employees.get(k).getUsername());
                    promotions.get(i).setEmpNumber(employees.get(k).getEmpNumber());
                    break;
                }
            }
            promotions.get(i).setChangeDateStr(simpleDateFormat.format(promotions.get(i).getChangeDate()));
        }
        return promotions;
    }

    @Override
    public Promotion getPromotionById(Integer id) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Position> positions=positionMapper.getAllPosition();
        Promotion promotion=promotionMapper.getPromotionById(id);
        Employee employee=employeeMapper.getEmployeeById(promotion.getEmpId());
        promotion.setEnpName(employee.getUsername());
        for (int j=0;j<positions.size();j++){
            if (promotion.getOldPosition()==positions.get(j).getId()){
                promotion.setOldPositionName(positions.get(j).getName());
            }
            if (promotion.getNewPosition()==positions.get(j).getId()){
                promotion.setNewPositionName(positions.get(j).getName());
            }
        }
        promotion.setChangeDateStr(simpleDateFormat.format(promotion.getChangeDate()));
        return promotion;
    }

    @Override
    public int addPromotion(Promotion promotion) {
        return promotionMapper.addPromotion(promotion);
    }

    @Override
    public int deletePromotion(Integer id) {
        return promotionMapper.deletePromotion(id);
    }

    /**
     * @param empId
     * @Description: 获取今年的晋升信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<Promotion> getPromotionByEmpIdYear(Integer empId) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Position> positions=positionMapper.getAllPosition();
        List<Promotion> promotions= promotionMapper.getPromotionByEmpIdYear(empId);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<promotions.size();i++){
            for (int j=0;j<positions.size();j++){
                if (promotions.get(i).getOldPosition()==positions.get(j).getId()){
                    promotions.get(i).setOldPositionName(positions.get(j).getName());
                }
                if (promotions.get(i).getNewPosition()==positions.get(j).getId()){
                    promotions.get(i).setNewPositionName(positions.get(j).getName());
                }
            }
            for (int k=0;k<employees.size();k++){
                if (employees.get(k).getId()==promotions.get(i).getEmpId()){
                    promotions.get(i).setEnpName(employees.get(k).getUsername());
                    promotions.get(i).setEmpNumber(employees.get(k).getEmpNumber());
                }
            }
            promotions.get(i).setChangeDateStr(simpleDateFormat.format(promotions.get(i).getChangeDate()));
        }
        return promotions;
    }

    @Override
    public List<Promotion> getPromotionByEmpId(Integer empId) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List<Position> positions=positionMapper.getAllPosition();
        List<Promotion> promotions= promotionMapper.getPromotionByEmpId(empId);
        List<Employee> employees=employeeMapper.getAll();
        for (int i=0;i<promotions.size();i++){
            for (int j=0;j<positions.size();j++){
                if (promotions.get(i).getOldPosition()==positions.get(j).getId()){
                    promotions.get(i).setOldPositionName(positions.get(j).getName());
                }
                if (promotions.get(i).getNewPosition()==positions.get(j).getId()){
                    promotions.get(i).setNewPositionName(positions.get(j).getName());
                }
            }
            for (int k=0;k<employees.size();k++){
                if (employees.get(k).getId()==promotions.get(i).getEmpId()){
                    promotions.get(i).setEnpName(employees.get(k).getUsername());
                    promotions.get(i).setEmpNumber(employees.get(k).getEmpNumber());
                }
            }
            promotions.get(i).setChangeDateStr(simpleDateFormat.format(promotions.get(i).getChangeDate()));
        }
        return promotions;
    }
}
