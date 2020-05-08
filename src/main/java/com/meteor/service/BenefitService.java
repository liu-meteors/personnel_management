package com.meteor.service;

import com.meteor.pojo.Benefit;

import java.util.List;

public interface BenefitService {
    int addBen(Benefit benefit);
    int updateBen(Benefit benefit);
    List<Benefit> getAllBen();
    int deleteBenById(Integer id);
    List<Benefit> getAllBenefitByYear();
    List<Benefit> getAllBenefitByDep(Integer dep);
    List<Benefit> getAllBenefitByDepYear(Integer dep);
}
