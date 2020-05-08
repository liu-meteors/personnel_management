package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/2 18:07
 * @description：面试类
 * @modified By：
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    private Integer id;
    private String name;
    private String phone;
    private String address;
    private Date viewDate;
    private String viewDateStr;
    private String mail;
    private Integer isView;
    private Integer isSend;
    private Integer isSuccess;
    private Integer department;
    private Integer posite;
    private String fileAddress;
    private String fileUrl;
    private String departmentName;
    private String positionName;
    private String isInterview;
}
