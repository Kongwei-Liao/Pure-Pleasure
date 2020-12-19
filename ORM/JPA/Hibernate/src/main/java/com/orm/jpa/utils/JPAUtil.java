package com.orm.jpa.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

    // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
    private static EntityManagerFactory em;

    // 使用静态代码块赋值
    static {
        em = Persistence.createEntityManagerFactory("WhatJPA");
        // System.out.println("静态代码块内容啥时候执行？？");
    }

    private JPAUtil() {}

    public static EntityManager getEntityManager() {
        return em.createEntityManager();
    }
}
