package com.xhlim.utils;

import com.xhlim.utils.constant.Constant;
import org.junit.Test;

import java.util.Locale;

/**
 * @author xhlim@outlook.com
 * @date 2018-10-22
 */
public class ConstantTest {

    @Test
    public void test() {
        System.out.println(Constant.SortOrder.ASC.toString());
        System.out.println(Constant.SortOrder.DESC.name());
        System.out.println(Locale.ROOT);
        Constant.Time time = Constant.Time.init("second");
        System.out.println(time.time());
    }
}
