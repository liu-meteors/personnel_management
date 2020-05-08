package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String username;
    private String phone;
    private String address;
    private String empNumber;
    private String password;
    private Integer department;
    private Integer posite;
    private Date signDate;
    private Date overDate;
    private Float salary;
    private String departmentName;
    private String positionName;
    private String signDateStr;
    private String overDateStr;
    private String isGrade;
    private String[] startToOver;
}
