package com.xhlim.utils.map;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhlim@outlook.com
 * @date 2018-08-04
 */
public class MapUtilsTest {

    Map<Object, Object> empty_map = Collections.EMPTY_MAP;
    Map<Object, Object> map = new HashMap<>();

    @Test
    public void isNullOrEmpty() {
        System.out.println(MapUtils.isNullOrEmpty(empty_map));

        System.out.println(MapUtils.isNullOrEmpty(map));

        map.clear();
        map.put("sd", "dd");
        System.out.println(MapUtils.isNullOrEmpty(map));

        map.clear();
        map.put(null, "dsd");
        System.out.println(MapUtils.isNullOrEmpty(map));

        map.clear();
        map.put(null, null);
        System.out.println(MapUtils.isNullOrEmpty(map));
    }

    @Test
    public void notNullAndEmpty() {
        System.out.println(MapUtils.notNullAndEmpty(empty_map));

        System.out.println(MapUtils.notNullAndEmpty(map));

        map.clear();
        map.put("sd", "dd");
        System.out.println(MapUtils.notNullAndEmpty(map));

        map.clear();
        map.put(null, "dsd");
        System.out.println(MapUtils.notNullAndEmpty(map));

        map.clear();
        map.put(null, null);
        System.out.println(MapUtils.notNullAndEmpty(map));
    }
}