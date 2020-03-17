package com.kang.pojo;

import lombok.Data;

/**
 * @author klr
 * @create 2020-03-17-15:13
 */
@Data
public class Student {
    //组合
    private int id;
    private String name;
    //学生需要关联一个老师
    private Teacher teacher;
}
