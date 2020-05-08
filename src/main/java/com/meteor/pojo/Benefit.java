package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Benefit {
    private Integer id;
    private String benYear;
    private float money;
    private Integer quarter;
    private String quarterStr;
    private Date fillInDate;
    private String fillInDateStr;
    private Integer isFillIn;
    private Integer department;
    private String departmentName;
}
