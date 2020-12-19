package com.orm.jpa.examples.crud;

import com.orm.jpa.entity.EntityDemo;
import com.orm.jpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Modify {
    public static void main(String[] args) {

        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //执行操作
            EntityDemo c1 = em.find(EntityDemo.class, 6);
            c1.setName("雀巢咖啡");
            // em.clear();//把c1对象从缓存中清除出去     // 好像没啥用啊
            em.merge(c1);
            //提交事务
            tx.commit();
        } catch (Exception e) {
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            //释放资源
            em.close();
        }
    }
}
