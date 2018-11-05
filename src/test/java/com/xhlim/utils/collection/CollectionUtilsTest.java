package com.xhlim.utils.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xhlim@outlook.com
 * @date 2018-08-04
 */
public class CollectionUtilsTest {
    List empty_list = Collections.EMPTY_LIST;
    List list = new ArrayList();

    @Test
    public void isNullOrEmpty() {
        System.out.println(CollectionUtils.isNullOrEmpty(empty_list));

        list.clear();
        list.add("test");
        System.out.println(CollectionUtils.isNullOrEmpty(list));

        list.clear();
        list.add(null);
        System.out.println(CollectionUtils.isNullOrEmpty(list));
    }

    @Test
    public void notNullAndEmpty() {
        System.out.println(CollectionUtils.notNullAndEmpty(empty_list));

        list.clear();
        list.add("test");
        System.out.println(CollectionUtils.notNullAndEmpty(list));

        list.clear();
        list.add(null);
        System.out.println(CollectionUtils.notNullAndEmpty(list));
    }
}