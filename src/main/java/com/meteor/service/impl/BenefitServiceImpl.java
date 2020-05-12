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
        System.out.println(benefit);
        Benefit oldBenefit=benefitMapper.getBenefitById(benefit.getId());
        oldBenefit.setIsFillIn(1);
        oldBenefit.setMoney(benefit.getMoney());
        oldBenefit.setFillInDate(new Date());
        return benefitMapper.updateBen(oldBenefit);
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
            System.out.println("无纸");
            Benefit benefit1=new Benefit();
            benefit1.setFillInDate(new Date());
            benefit1.setDepartment(dep);
            int quarter=getQuarter(month);
            if (quarter==1){
                benefit1.setQuarter(4);
                benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)-1));
            }else {
                benefit1.setQuarter(quarter-1);
                benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));

            }
            benefit1.setMoney(0);
            benefit1.setIsFillIn(0);
            benefit1=setDepartmentName(benefit1,departments);
            benefitMapper.addBen(benefit1);
            benefitList.add(benefit1);
        }else {
            System.out.println("youzhi");
            Benefit benefit=getNoFill(benefitList.get(benefitList.size()-1),departments);
            if(benefit!=null){
                System.out.println("charu");
                benefitMapper.addBen(benefit);
            }
            benefitList=benefitMapper.getAllBenefitByDepYear(dep);
            for (int i=0;i<benefitList.size();i++){
                benefitList.set(i,setDepartmentName(benefitList.get(i),departments));
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
    /**
            * @Description: 如果当前季度应填的效益没有填，则添加
            * @Param:  * @Param: benefit
     * @Param: departments
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    public Benefit getNoFill(Benefit benefit,List<Department> departments){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        Benefit benefit1=new Benefit();
        int quarter=getQuarter(calendar.get(Calendar.MONTH)+1);
        if (Math.abs(quarter-benefit.getQuarter())==2){
            System.out.println("上季度没有填写");
            if (quarter==1){
                benefit1.setQuarter(4);
                benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)-1));
            }else {
                benefit1.setQuarter(quarter-1);
                benefit1.setBenYear(String.valueOf(calendar.get(Calendar.YEAR)));

            }
            benefit1.setFillInDate(new Date());
            benefit1.setDepartment(benefit.getDepartment());
            benefit1.setMoney(0);
            benefit1.setIsFillIn(0);

            return benefit1;
        }else {
            System.out.println("上季度填写了");
            return null;
        }

    }
}
