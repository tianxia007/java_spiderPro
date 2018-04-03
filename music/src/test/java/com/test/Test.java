package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/03
 */

public class Test {
    @org.junit.Test
    public void test1() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("hi");
    }
}
