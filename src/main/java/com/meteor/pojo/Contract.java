package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/7 13:51
 * @description：合同实体类
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Integer id;
    private Integer department;
    private Integer posite;
    private String fileAddress;
    private String departmentName;
    private String positionName;
}
