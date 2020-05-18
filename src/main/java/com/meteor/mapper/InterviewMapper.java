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

    /**
     * @Description: 获取当天面试信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Interview> getAllInterviewNow();

    Interview getInterviewById(Integer id);

    /**
     * @Description: 获取该部门当天的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Interview> getAllInterviewNowByDep(Integer dep);

    /**
     * @Description: 获取该部门当所有的面试情况
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Interview> getAllInterviewByDep(Integer dep);
}
