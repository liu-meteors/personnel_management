package com.meteor.service;

import com.meteor.pojo.Leave;

import java.util.List;

public interface LeaveService {
    List<Leave> getAllLeave();
    List<Leave> getAllDepApprove();
    List<Leave> getAllApprove();
    List<Leave> getAllByEmpId(Integer id);
    int updateLeave(Leave leave);
    int addLeave(Leave leave);
    Leave getLeaveById(Integer id);
    List<Leave> getAllLeaveNow(Integer id);
    List<Leave> getAllLeaveByNow();
}
