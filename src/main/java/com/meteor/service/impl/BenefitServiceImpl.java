package com.meteor.service.impl;

import com.meteor.mapper.BenefitMapper;
import com.meteor.pojo.Benefit;
import com.meteor.pojo.Department;
import com.meteor.service.BenefitService;
import com.meteor.service.DepartmentService;
import com.meteor.untils.DateUtils;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/4 10:55
 * @description：效益处理
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class BenefitServiceImpl implements BenefitService {
    @Autowired
    BenefitMapper benefitMapper;
    @Autowired
    DepartmentService departmentService;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int addBen(Benefit benefit) {
        benefit.setFillInDate(new Date());
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int month=calendar.get(Calendar.MONTH)+1;
        benefit.setQuarter(getQuarter(month));
        benefit.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));
        benefit.setIsFillIn(1);
        return benefitMapper.addBen(benefit);
    }

    @Override
    public int updateBen(Benefit benefit) {
        return updateBen(benefit);
    }

    @Override
    public List<Benefit> getAllBen() {
        List<Department> departments=departmentService.getAll();
        List<Benefit> benefitList=benefitMapper.getAllBen();
        for (int i=0;i<benefitList.size();i++){
            benefitList.set(i,setDepartmentName(benefitList.get(i),departments));
        }
        return benefitList;
    }

    @Override
    public int deleteBenById(Integer id) {
        return deleteBenById(id);
    }

    @Override
    public List<Benefit> getAllBenefitByYear() {
        List<Department> departments=departmentService.getAll();
        List<Benefit> benefitList=benefitMapper.getAllBenefitByYear();
        for (int i=0;i<benefitList.size();i++){
            benefitList.set(i,setDepartmentName(benefitList.get(i),departments));
        }
        return benefitList;
    }

    @Override
    public List<Benefit> getAllBenefitByDep(Integer dep) {
        List<Department> departments=departmentService.getAll();
        List<Benefit> benefitList=benefitMapper.getAllBenefitByDep(dep);
        for (int i=0;i<benefitList.size();i++){
            benefitList.set(i,setDepartmentName(benefitList.get(i),departments));
        }
        return benefitList;
    }

    @Override
    public List<Benefit> getAllBenefitByDepYear(Integer dep) {
        List<Department> departments=departmentService.getAll();
        List<Benefit> benefitList=benefitMapper.getAllBenefitByDepYear(dep);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        int month=calendar.get(Calendar.MONTH)+1;
        if (benefitList.size()==0){
            Benefit benefit1=new Benefit();
            benefit1.setFillInDate(new Date());
            benefit1.setDepartment(dep);
            benefit1.setQuarter(getQuarter(month));
            benefit1.setId(1);
            benefit1.setMoney(0);
            benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));
            benefit1.setIsFillIn(0);
            benefit1=setDepartmentName(benefit1,departments);
            benefitList.add(benefit1);
        }else {

            for (int i=0;i<benefitList.size();i++){
                benefitList.set(i,setDepartmentName(benefitList.get(i),departments));
            }
            Benefit benefit=getNoFill(benefitList.get(benefitList.size()-1),departments);
            if(benefit!=null){
                benefitList.add(benefit);
            }
        }
        System.out.println("今年的效益信息：：："+benefitList);
        return benefitList;
    }

    public Integer getQuarter(Integer month){
        if (month>=1&&month<=3){
            return 1;
        }else if (month>=4&&month<=6){
            return 2;
        }else if (month>=7&&month<=9){
            return 3;
        }else {
            return 4;
        }
    }


    public String setQuarterStr(Integer quarter){
        switch (quarter){
            case 1:
                return "第一季度";
            case 2:
                return "第二季度";
            case 3:
                return "第三季度";
            case 4:
                return "第四季度";
                default:
                return null;
        }
    }

    public Benefit setDepartmentName(Benefit benefit, List<Department> departments){
        benefit.setFillInDateStr(DateUtils.getDateStr(simpleDateFormat,benefit.getFillInDate()));
        benefit.setQuarterStr(setQuarterStr(benefit.getQuarter()));
        for (int i=0;i<departments.size();i++){
            if (benefit.getDepartment()==departments.get(i).getId()){
                benefit.setDepartmentName(departments.get(i).getName());
                break;
            }
        }
        return benefit;
    }

    public Benefit getNoFill(Benefit benefit,List<Department> departments){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Benefit benefit1=new Benefit();
        int quarter=getQuarter(calendar.get(Calendar.MONTH)+1);
        if (quarter!=benefit.getQuarter()){

            benefit1.setFillInDate(new Date());
            benefit1.setDepartment(benefit.getDepartment());
            benefit1.setQuarter(quarter);
            benefit1.setId(benefit.getId()+1);
            benefit1.setMoney(0);
            benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));
            benefit1.setIsFillIn(0);
            benefit1=setDepartmentName(benefit1,departments);
            return benefit1;
        }else if (quarter-benefit.getQuarter()>0){
            benefit1.setFillInDate(new Date());
            benefit1.setDepartment(benefit.getDepartment());
            benefit1.setQuarter(quarter);
            benefit1.setId(benefit.getId()+1);
            benefit1.setMoney(0);
            benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));
            benefit1=setDepartmentName(benefit1,departments);
            benefit1.setIsFillIn(1);
            benefitMapper.addBen(benefit1);
            return benefit1;
        }else {
            return null;
        }
    }
}
