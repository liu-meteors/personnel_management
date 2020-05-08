package com.meteor.untils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/8 21:24
 * @description：日期处理类
 * @modified By：
 * @version: 0.0.1$
 */
public class DateUtils {
   static Calendar calendar=Calendar.getInstance();

    public static Date getTrueDate(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DATE,2);
        return calendar.getTime();
    }
    public static Date dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            oldDate= oldDate.replace("Z", " UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


    public static String getDateStr(SimpleDateFormat simpleDateFormat,Date date){
        return simpleDateFormat.format(date);
    }
}
