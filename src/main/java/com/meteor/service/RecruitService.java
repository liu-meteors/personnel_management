package com.meteor.service;

import com.meteor.pojo.Recruit;

import java.util.List;

public interface RecruitService {
    public Integer addRecruit(Recruit recruit);
    public List<Recruit> getAllRecruit();
    public Integer deleteRecruitById(Integer id);
    public Recruit getRecruitById(Integer id);
    public Integer updateRecruit(Recruit recruit);
}
