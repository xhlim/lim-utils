package com.xhlim.utils.map;

import java.util.Map;

/**
 * map 集合
 *
 * @author xhlim@outlook.com
 * @create 2018-06-29
 */
public class MapUtils {

    /**
     * 集合为空
     *
     * @param map
     * @return 为null、size==0 时返回 TRUE
     */
    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 集合不为空
     *
     * @param map
     * @return 不为null、size!=0 时返回 TRUE
     */
    public static boolean notNullAndEmpty(Map<?, ?> map) {
        return !isNullOrEmpty(map);
    }

}
