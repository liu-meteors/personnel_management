package com.meteor.service.impl;

import com.meteor.mapper.InterviewMapper;
import com.meteor.mapper.PositionMapper;
import com.meteor.pojo.Department;
import com.meteor.pojo.Interview;
import com.meteor.pojo.Position;
import com.meteor.service.DepartmentService;
import com.meteor.service.InterviewService;
import com.meteor.untils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/6 16:03
 * @description：面试处理类
 * @modified By：
 * @version: 0.0.1$
 */
@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    InterviewMapper interviewMapper;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionMapper positionMapper;

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public int addInterview(Interview interview) {
        interview.setIsSend(0);
        interview.setIsView(0);
        interview.setViewDate(DateUtils.dealDateFormat(interview.getViewDateStr()));
        System.out.println(interview);
        return interviewMapper.addInterview(interview);
    }

    @Override
    public int deleteInterviewById(Integer id) {
        return interviewMapper.deleteInterviewById(id);
    }

    @Override
    public int updateInterviewById(Interview interview) {
        return interviewMapper.updateInterviewById(interview);
    }

    @Override
    public List<Interview> getAllInterview() {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterview();
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }

    @Override
    public List<Interview> getAllInterviewNow() {
        List<Department> departments=departmentService.getAll();
        List<Position> positions=positionMapper.getAllPosition();
        List<Interview> interviews=interviewMapper.getAllInterviewNow();
        for (int i=0;i<interviews.size();i++){
            interviews.set(i,setInterViewInformation(interviews.get(i),departments,positions));
        }
        return interviews;
    }

    @Override
    public Interview getInterviewById(Integer id) {
        Interview interview=interviewMapper.getInterviewById(id);
        return interview;
    }


    public Interview setInterViewInformation(Interview interview, List<Department> departments, List<Position> positions){
        for (int i=0;i<departments.size();i++){
            if (departments.get(i).getId()==interview.getDepartment()){
                interview.setDepartmentName(departments.get(i).getName());
                break;
            }
        }
        for (int i=0;i<positions.size();i++){
            if (positions.get(i).getId()==interview.getPosite()){
                interview.setPositionName(positions.get(i).getName());
            }
        }
        if (interview.getIsView()==0){
            interview.setIsInterview("未面试");
        }else if (interview.getIsView()==1){
            if (interview.getIsSend()==0){
                interview.setIsInterview("已面试");
            }else {
                interview.setIsInterview("已发送");
            }
        }else {
            interview.setIsInterview("已拒绝");

        }
        interview.setViewDateStr(simpleDateFormat.format(interview.getViewDate()));
        return interview;

    }
}
