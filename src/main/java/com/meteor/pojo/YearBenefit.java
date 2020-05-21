package com.meteor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/5/21 9:37
 * @description：年效益实体类
 * @modified By：
 * @version: 0.0.1$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearBenefit {
   private Integer id;
   private String yearDate;
   private Float money;
   private Integer department;
}
