package com.meteor.mapper;

import com.meteor.pojo.Interview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InterviewMapper {
    int addInterview(Interview interview);
    int deleteInterviewById(Integer id);
    int updateInterviewById(Interview interview);
    List<Interview> getAllInterview();
    List<Interview> getAllInterviewNow();
    /** 
            * @Description: 获取当天面试信息 
            * @Param:  * @Param: id
            * @return: 
            * @Author: liujingyu
            * @Date: 
            */ 
    Interview getInterviewById(Integer id);
}
