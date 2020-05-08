package com.meteor.service;

import com.meteor.pojo.Interview;

import java.util.List;

public interface InterviewService {
    int addInterview(Interview interview);
    int deleteInterviewById(Integer id);
    int updateInterviewById(Interview interview);
    List<Interview> getAllInterview();
    List<Interview> getAllInterviewNow();
    Interview getInterviewById(Integer id);
}
