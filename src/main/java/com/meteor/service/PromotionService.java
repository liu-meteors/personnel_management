package com.meteor.service;

import com.meteor.pojo.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getAllPromotion();
    Promotion getPromotionById(Integer id);
    int addPromotion(Promotion promotion);
    int deletePromotion(Integer id);
    List<Promotion> getPromotionByEmpId(Integer empId);
}
