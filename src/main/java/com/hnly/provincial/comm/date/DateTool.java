package com.hnly.provincial.comm.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-23
 */
public class DateTool {
    private DateTool(){}

    /**
     * 获取系统当前年
     *
     * @return 系统当前年
     */
    public static int getYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    /**
     * 获取去年
     *
     * @return {@link int}
     */
    public static int getLastYear() {
        return getYear() - 1;
    }

    /**
     * 获取系统当前年月日
     *
     * @return 系统当前年月日
     */
    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
