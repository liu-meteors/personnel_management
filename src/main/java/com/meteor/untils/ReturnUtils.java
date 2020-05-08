package com.meteor.untils;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/7 21:16
 * @description：返回工具类
 * @modified By：
 * @version: 0.0.1$
 */
public class ReturnUtils {
    public static String isSuccess(int isSuccess){
        if (isSuccess>0){
            return "success";
        }else {
            return "error";
        }
    }
}
