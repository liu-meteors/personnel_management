package com.meteor.service.impl;

import com.meteor.mapper.YearBenefitMapper;
import com.meteor.pojo.Benefit;
import com.meteor.pojo.Department;
import com.meteor.pojo.YearBenefit;
import com.meteor.service.BenefitService;
import com.meteor.service.DepartmentService;
import com.meteor.service.YearBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/21 9:56
 * @description：年效益处理类
 * @modified By：
 * @version: 0.0..1$
 */
@Service
public class YearBenefitServiceImpl implements YearBenefitService {
    @Autowired
    YearBenefitMapper yearBenefitMapper;
    @Autowired
    BenefitService benefitService;
    @Autowired
    DepartmentService departmentService;
    /**
     * @param yearBenefit
     * @Description: 添加年效益
     * @Param: * @Param: yearBenefit
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int addYearBenefit(YearBenefit yearBenefit) {
        return yearBenefitMapper.addYearBenefit(yearBenefit);
    }

    /**
     * @Description: 查询所有年效益
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<YearBenefit> getAllYearBenefit() {
        return yearBenefitMapper.getAllYearBenefit();
    }

    /**
     * @param dep
     * @Description: 查询部门年效益
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public List<YearBenefit> getDepYearBenefit(Integer dep) {
        return yearBenefitMapper.getDepYearBenefit(dep);
    }

    /**
     * @param yearBenefit
     * @Description: 修改年效益
     * @Param: * @Param: yearBenefit
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int updateYearBenefit(YearBenefit yearBenefit) {
        return yearBenefitMapper.updateYearBenefit(yearBenefit);
    }

    /**
     * @param id
     * @Description: 删除年效益
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public int deleteYearBenefitById(Integer id) {
        return yearBenefitMapper.deleteYearBenefitById(id);
    }

    /**
     * @Description: 判断需不需要添加年效益
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    @Override
    public void isAddYearBenefit() {
        Calendar calendar=Calendar.getInstance();
        List<Benefit> benefitList=benefitService.getAllBen();
        List<Department> departments=departmentService.getAll();
        for (Department department:departments){
            //获取所有该部门的年效益
            List<YearBenefit> yearBenefits=yearBenefitMapper.getDepYearBenefit(department.getId());
            //判断有年效益记录
            if (yearBenefits.size()!=0){
                //判断上一年没有记录
                if (!String.valueOf(calendar.get(Calendar.YEAR)-1).equals(yearBenefits.get(0).getYearDate())){
                    YearBenefit yearBenefit=new YearBenefit();
                    float money=0.0f;
                    for (Benefit benefit:benefitList){
                        //判断相同部门的季度效益
                        if (benefit.getDepartment()==department.getId()){
                            // 判断季度效益的年份要与上一年相同
                            if (String.valueOf(calendar.get(Calendar.YEAR)-1).equals(benefit.getBenYear())){
                                money+=benefit.getMoney();
                            }
                        }
                    }
                    //循环完成后添加到数据库中
                    yearBenefit.setMoney(money);
                    yearBenefit.setDepartment(department.getId());
                    yearBenefit.setYearDate(String.valueOf(calendar.get(Calendar.YEAR)-1));
                }
            }else {
                YearBenefit yearBenefit=new YearBenefit();
                float money=0.0f;
                for (Benefit benefit:benefitList){
                    //判断相同部门的季度效益
                    if (benefit.getDepartment()==department.getId()){
                        // 判断季度效益的年份要与上一年相同
                        if (String.valueOf(calendar.get(Calendar.YEAR)-1).equals(benefit.getBenYear())){
                            money+=benefit.getMoney();
                        }
                    }
                }
                //循环完成后添加到数据库中
                yearBenefit.setMoney(money);
                yearBenefit.setDepartment(department.getId());
                yearBenefit.setYearDate(String.valueOf(calendar.get(Calendar.YEAR)-1));
            }


        }
    }
}
