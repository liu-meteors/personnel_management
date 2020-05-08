package com.meteor.mapper;

import com.meteor.pojo.Promotion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PromotionMapper {
    List<Promotion> getAllPromotion();
    Promotion getPromotionById(Integer id);
    int addPromotion(Promotion promotion);
    int deletePromotion(Integer id);
    List<Promotion> getPromotionByEmpId(Integer empId);


}
