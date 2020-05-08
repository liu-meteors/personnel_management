package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/23 14:23
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    private Integer id;
    private Date applyDate;
    private Date startDate;
    private Date overDate;
    private Integer empId;
    private Integer depApprove;
    private Integer hrApprove;
    private String isCheck;
    private String enpName;
    private String applyDateStr;
    private String startDateStr;
    private String overDateStr;
    private String leaveDesc;
    private String[] startToOver;
    private String empNumber;

}
