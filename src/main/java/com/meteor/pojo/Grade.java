package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/26 16:00
 * @description：考核成绩实体类
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private Integer id;
    private Integer grade;
    private Integer toGradeEmpId;
    private Integer fromGradeEmpId;
    private Date gradeDate;
    private String toName;
    private String fromName;


}
