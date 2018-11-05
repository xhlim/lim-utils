package com.xhlim.utils.json.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
}
