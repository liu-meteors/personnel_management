package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/25 17:46
 * @description：考核成绩历史
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeHistory {
    private Integer id;
    private Integer empId;
    private Date gradeDate;
    private String gradeDateStr;
    private Integer grade;
    private String empName;
    private String departmentName;
    private String positionName;
    private String empNumber;
}
