package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/6 23:03
 * @description：招聘实体类
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recruit {
    private   Integer id;
    private Integer department;
    private Integer position;
    private Float salary;
    private String information;
    private String departmentName;
    private String positionName;

}
