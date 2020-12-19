package com.orm.jpa.examples.crud;

import com.orm.jpa.entity.EntityDemo;
import com.orm.jpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Delete {
    public static void main(String[] args) {

        // 定义对象
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            // 获取实体管理对象
            em = JPAUtil.getEntityManager();

            // 这里体现静态代码块的执行时机
//            JPAUtil.getEntityManager();
//            JPAUtil.getEntityManager();
//            JPAUtil.getEntityManager();
//            JPAUtil.getEntityManager();

            // 获取事务对象
            tx = em.getTransaction();
            // 开启事务
            tx.begin();
            // 执行操作
            EntityDemo entityDemo = em.find(EntityDemo.class, 1);
            System.out.println(entityDemo.getId());
            em.remove(entityDemo);
            // 提交事务
            tx.commit();
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
