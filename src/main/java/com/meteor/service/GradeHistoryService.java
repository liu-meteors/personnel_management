package com.meteor.service;

import com.meteor.pojo.GradeHistory;

import java.util.List;

public interface GradeHistoryService {
    /**
     * @Description: 添加记录
     * @Param: * @Param: gradeHistory
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int addGradeHistory(GradeHistory gradeHistory);

    /**
     * @Description: 获取所有考核成绩历史
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<GradeHistory> getAllGradeHistory();

    /**
     * @Description: 查询上个月的考核成绩
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<GradeHistory> getAllGradeHistoryNow();

    /**
     * @Description: 根据部门查询历史考核成绩
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<GradeHistory> getGradeHistoryByDep(Integer dep);

    /**
     * @Description: 查询上个月部门的历史考核成绩
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<GradeHistory> getGradeHistoryByDepNow(Integer dep);
}
