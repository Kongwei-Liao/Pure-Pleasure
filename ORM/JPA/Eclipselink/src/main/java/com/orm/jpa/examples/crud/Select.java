package com.orm.jpa.examples.crud;

import com.orm.jpa.entity.EntityDemo;
import com.orm.jpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Select {
    public static void main(String[] args) {

        // 定义对象
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 获取实体管理对象
            em = JPAUtil.getEntityManager();
            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作
            EntityDemo entityDemo = em.find(EntityDemo.class, 2);
            // 提交事务
            tx.commit();
            System.out.println(entityDemo.toString()); // 输出查询对象
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }

        /**
         * EntityManager具有缓存功能
         */
        try {
            // 获取实体管理对象
            em = JPAUtil.getEntityManager();
            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作
            EntityDemo entityDemo1 = em.find(EntityDemo.class, 2);
            EntityDemo entityDemo2 = em.find(EntityDemo.class, 2);
            System.out.println(entityDemo1 == entityDemo2);// 输出结果是true，可以看出EntityManager具有缓存功能
            // 提交事务
            tx.commit();
            System.out.println(entityDemo1);
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }


        /**
         * 查询一个： 使用延迟加载策略
         */
        try {
            // 获取实体管理对象
            em = JPAUtil.getEntityManager();
            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作
            EntityDemo c1 = em.getReference(EntityDemo.class, 2);
            // 提交事务
            tx.commit();
            // System.out.println(c1);   // 这一行注释掉，可以发现不会进行实际的查询操作。就是说仅有这个对象被用到的时候，才会触发查询操作
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }
    }
}
