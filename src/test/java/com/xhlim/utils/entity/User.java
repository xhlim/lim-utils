package com.xhlim.utils.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xhlim@outlook.com
 * @create 2018-06-07
 */
@Getter
@Setter
@ToString
public class User {
    private String name;
    private Integer age;
    private Boolean isRegist;
}
