package com.purepleasure.pepsicola.database.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "student")
public class Student {

    @Id//声明当前私有属性为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name = "sid") //指定和表中cust_id字段的映射关系
    private Long id;

    @Column(name = "sname")
    private String name;

    @Column(name = "sage")
    private Date age;

    @Column(name = "ssex")
    private String sex;


}
