package com.xhlim.utils.id;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xhlim@outlook.com
 * @date 2018-09-28
 */
public class SnowflakeIdWorkerTest {

    @Test
    public void test() {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            System.out.println(StringUtils.leftPad(Long.toBinaryString(id), 64, "0"));
        }
    }

}