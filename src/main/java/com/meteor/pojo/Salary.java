package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/29 18:15
 * @description：工资实体类
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
    private Integer id;
    private Integer empId;
    private Date payDate;
    private Float money;
    private Float bonus;
    private Float forfeit;
    private Float gradeMoney;
    private Float allMoney;
    private String name;
    private String empNumber;
}
