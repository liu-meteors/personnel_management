package com.meteor.service;

import com.meteor.pojo.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotion();

    Promotion getPromotionById(Integer id);

    int addPromotion(Promotion promotion);

    int deletePromotion(Integer id);

    /**
     * @Description: 获取今年的晋升信息
     * @Param: * @Param: empId
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Promotion> getPromotionByEmpIdYear(Integer empId);

    List<Promotion> getPromotionByEmpId(Integer empId);

    /**
     * @Description: 获取部门奖惩信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Promotion> getProByDep(Integer dep);
}
