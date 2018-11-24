package com.xhlim.utils.yaml.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-07
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User {
    private String name;
    private Integer age;
    private Boolean isRegist;
    private Child child;
    private List<Child> childs;

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    public static class Child {
        private String name;
        private Integer age;
        private Boolean isRegist;
    }
}
