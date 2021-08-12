package com.cp.sendmq.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author yyds
 * @since 2021-08-11
 */
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;


    private String id;


    private String name;


    private LocalDateTime age;


    private String sex;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAge() {
        return age;
    }

    public void setAge(LocalDateTime age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
