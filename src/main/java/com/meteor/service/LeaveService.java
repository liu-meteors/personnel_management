package com.meteor.service;

import com.meteor.pojo.Leave;

import java.util.List;

public interface LeaveService {
    /**
     * @Description: 获取所有请假信息
     * @Param: * @Param:
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Leave> getAllLeave();

    List<Leave> getAllDepApprove();

    List<Leave> getAllApprove();

    /**
     * @Description: 获取某个员工所有请假信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Leave> getAllByEmpId(Integer id);

    int updateLeave(Leave leave);

    int addLeave(Leave leave);

    Leave getLeaveById(Integer id);

    /**
     * @Description: 获取某个员工当月所有信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Leave> getAllLeaveNow(Integer id);

    List<Leave> getAllLeaveByNow();

    /**
     * @Description: 获取部门所有请假信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Leave> getAllDepLeave(Integer dep);

    /**
     * @Description: 获取部门当月所有请假信息
     * @Param: * @Param: dep
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    List<Leave> getLeaveByDepMonth(Integer dep);

    /**
     * @Description: 删除请假信息
     * @Param: * @Param: id
     * @return:
     * @Author: liujingyu
     * @Date:
     */
    int deleteLeaveById(Integer id);
}
