package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/15 11:25
 * @description：合同历史
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractHistory {
    private Integer id;
    private String fileAddress;
    private Integer empId;
    private Date submitDate;
    private String username;
    private String empNumber;
    private String submitDateStr;
}
