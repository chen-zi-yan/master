package com.hnly.provincial.comm.date;

import java.time.LocalDate;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-23
 */
public class DateTool {

    /**
     * 获取当前年
     *
     * @return 当前年
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
}
