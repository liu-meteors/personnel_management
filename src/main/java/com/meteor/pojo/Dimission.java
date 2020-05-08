package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/7 23:54
 * @description：离职登记
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dimission {
    private Integer id;
    private String username;
    private String empNumber;
    private Date leaveDate;
    private String leaveDateStr;
    private Integer department;
    private Integer position;
    private String handover;
    private Integer isOver;
    private String departmentName;
    private String positionName;
    private boolean delivery;
    private String isOverStr;
    private String phone;
}
