package com.orm.jpa.examples.crud;

import com.orm.jpa.entity.Address;
import com.orm.jpa.entity.Customer;
import com.orm.jpa.entity.EntityDemo;
import com.orm.jpa.entity.mapping.Library;
import com.orm.jpa.entity.mapping.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Create {
    public static void main(String[] args) {


        /**
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        // createEntityManagerFactory 这里创建的是由Hibernate实现的EntityManagerFactory
        // 线程安全的
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("WhatJPA");

        // 创建实体管理类
        // JPA规范中，EntityManager是完成持久化操作的核心对象
        // createEntityManager这里创建的是由Hibernate实现的EntityManager
        EntityManager em = factory.createEntityManager();

        // 获取事务对象
        // getTransaction这里获取的是由Hibernate的事务管理
        EntityTransaction tx = em.getTransaction();

        // 开启事务
        tx.begin();

        Customer customer = new Customer();
        Address address = new Address();
        address.setE_city("beijing");
        address.setE_pincode(100001);
        address.setE_state("china");
        customer.getCustAddress().add(address);
        customer.setCustName("雀巢咖啡");
        customer.setCustPhone("1234567890");
        customer.setCustLevel("5L");
        customer.setCustSource("School");
        customer.setCustIndustry("大美中心");

        EntityDemo entityDemo = new EntityDemo();
        entityDemo.setAge(22);
        entityDemo.setName("小明");
        entityDemo.setGender("男");
        entityDemo.setBirthday(new Date());

        // 保存操作
        em.persist(customer);
        em.persist(entityDemo);

        // 下面不是创建，而是把之前刚刚插入的entityDemo记录更新
        entityDemo.setAge(21);
        entityDemo.setName("小花");
        entityDemo.setGender("女");
        entityDemo.setBirthday(new Date());
        em.persist(entityDemo);


        // @OneToOne 实例
        Student st1 = new Student();
        // st1.setS_id(1);   // 已配置主键生成策略，便不能set主键
        st1.setS_name("Maxsu");

        Student st2 = new Student();
        // t2.setS_id(2);
        st2.setS_name("James");

        em.persist(st1);
        em.persist(st2);

        Library lib1 = new Library();
        // lib1.setB_id(101);
        lib1.setB_name("Data Structure");
        lib1.setStud(st1);

        Library lib2 = new Library();
        // lib2.setB_id(102);
        lib2.setB_name("DBMS");
        lib2.setStud(st2);

        em.persist(lib1);
        em.persist(lib2);

        //

        tx.commit();

        // 释放资源
        em.close();
        factory.close();
    }
}
