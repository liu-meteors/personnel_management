package com.meteor.mapper;

import com.meteor.pojo.Recruit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RecruitMapper {
    public Integer addRecruit(Recruit recruit);
    public List<Recruit> getAllRecruit();
    public Integer deleteRecruitById(Integer id);
    public Recruit getRecruitById(Integer id);
    public Integer updateRecruit(Recruit recruit);
}
