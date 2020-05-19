package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/3 18:22
 * @description：奖惩信息
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award {
    private Integer id;
    private Integer empId;
    private Float money;
    private Integer awardType;
    private String awardDescribe;
    private Date recordDate;
    private String recordDateStr;
    private String empName;
    private String typeName;
    private String empNumber;
}
