package com.meteor.service;

import com.meteor.pojo.Interview;

import java.util.List;

public interface InterviewService {
    /**
            * @Description: 添加面试信息
            * @Param:  * @Param: interview
            * @return:
            * @Author: liujingyu
            * @Date:
            */
    int addInterview(Interview interview);
    int deleteInterviewById(Integer id);
    int updateInterviewById(Interview interview);
    List<Interview> getAllInterview();
    /**
     * @Description: 获取当天面试信息
     * @Param:  * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Interview> getAllInterviewNow();
    Interview getInterviewById(Integer id);
    /**
     * 发送一个简单的文本邮件，可以附带附件：文本邮件发送的基本方法
     * @param subject ：邮件主题，即邮件的邮件名称
     * @param content ：邮件内容
     * @param toWho ：需要发送的人
     * @param ccPeoples ：需要抄送的人
     * @param bccPeoples ：需要密送的人
     * @param attachments ：需要附带的附件，附件请保证一定要存在，否则将会被忽略掉
     * @return
     */
    int sendSimpleTextMailActual(String subject, String content, String[] toWho, String[] ccPeoples, String[] bccPeoples, String[] attachments);


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
