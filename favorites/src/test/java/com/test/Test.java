package com.test;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class Test {
    @org.junit.Test
    public void timeDate() {

        System.out.println(DateUtil.format(DateUtil.calendar(1476682829l * 1000).getTime(), DatePattern.NORM_DATETIME_FORMAT));
        System.out.println(DateUtil.date(1476682829l * 1000).toString(DatePattern.NORM_DATETIME_FORMAT));
        System.out.println(DateUtil.date(1522139302 * 1000).toString(DatePattern.NORM_DATETIME_FORMAT));
    }

    @org.junit.Test
    public void dir() {
        String base = System.getProperty("user.dir");
        String base2 = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

        System.out.println(base);
        System.out.println(base2);
    }

}
