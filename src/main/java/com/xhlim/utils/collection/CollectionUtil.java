package com.xhlim.utils.collection;

import java.util.Collection;

/**
 * 集合
 *
 * @author xhlim@outlook.com
 * @create 2018-06-29
 */
public class CollectionUtil {

    /**
     * 集合为空
     *
     * @param collection
     * @return 为null、size==0 时返回 TRUE
     */
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合不为空
     *
     * @param collection
     * @return 不为null、size!=0 时返回 TRUE
     */
    public static boolean notNullAndEmpty(Collection<?> collection) {
        return !isNullOrEmpty(collection);
    }
}
