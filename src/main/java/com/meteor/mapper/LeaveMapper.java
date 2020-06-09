package com.meteor.mapper;

import com.meteor.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LeaveMapper {
    List<Leave> getAllLeave();
    List<Leave> getAllDepApprove();
    List<Leave> getAllApprove();
    List<Leave> getAllLeaveNow(Integer id);
    List<Leave> getAllByEmpId(Integer id);
    int updateLeave(Leave leave);
    int addLeave(Leave leave);
    Leave getLeaveById(Integer id);
    List<Leave> getAllLeaveByNow();
    int deleteLeaveById(Integer id);
}
