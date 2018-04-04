package com.favorites.common;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class RandomUtil {
    public static long randomSleep() {
        return 1000 * cn.hutool.core.util.RandomUtil.randomInt(2, 5);
    }
}
