package com.pichs.xsql.demo;

import android.app.Activity;

import com.pichs.xsql.annotation.XSqlField;
import com.pichs.xsql.annotation.XSqlTable;


@XSqlTable("user_info")
public class UserInfo {


    @XSqlField("name")
    public String name;

    @XSqlField("age")
    public Integer age;

    public Activity activity;


    public UserInfo() {
    }

    public UserInfo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
