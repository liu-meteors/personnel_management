package com.meteor.mapper;

import com.meteor.pojo.Benefit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BenefitMapper {
    int addBen(Benefit benefit);
    int updateBen(Benefit benefit);
    List<Benefit> getAllBen();
    int deleteBenById(Integer id);
    List<Benefit> getAllBenefitByYear();
    List<Benefit> getAllBenefitByDep(Integer dep);
    List<Benefit> getAllBenefitByDepYear(Integer dep);
    Benefit getBenefitById(Integer id);

}
