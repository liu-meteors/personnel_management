package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/13 13:54
 * @description：晋升
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    private Integer id;
    private Integer empId;
    private Integer oldPosition;
    private Integer newPosition;
    private Date changeDate;
    private String transferred;
    private String oldPositionName;
    private String newPositionName;
    private String changeDateStr;
    private String enpName;
    private String empNumber;
}
