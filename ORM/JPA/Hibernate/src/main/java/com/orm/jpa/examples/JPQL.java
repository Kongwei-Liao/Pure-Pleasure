package com.orm.jpa.examples;

import com.orm.jpa.entity.EntityDemo;
import com.orm.jpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 1、查询所有
 * 2、分页查询
 * 3、Where 条件查询
 * 4、DESC 倒序查询
 * 5、Count 统计查询
 */
public class JPQL {
    public static void main(String[] args) {
        EntityManager em = null;
        EntityTransaction tx = null;

        /**
         * 查询所有
         */
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            // 创建query对象
            String jpql = "from EntityDemo";
            Query query = em.createQuery(jpql);
            // 查询并得到返回结果
            List list = query.getResultList(); // 得到集合返回类型
            for (Object object : list) {
                System.out.println(object);
            }
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }


        /**
         * 分页查询
         */
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();

            //创建query对象
            String jpql = "from EntityDemo";
            Query query = em.createQuery(jpql);
            //起始索引
            query.setFirstResult(0);
            //每页显示条数
            query.setMaxResults(2);
            //查询并得到返回结果
            List list = query.getResultList(); //得到集合返回类型
            for (Object object : list) {
                System.out.println(object);
            }
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }


        /**
         * Where 条件查询
         */
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            //创建query对象
            String jpqlSingle = "from EntityDemo where name like ?1 ";
            Query querySingle = em.createQuery(jpqlSingle);

            String jpqlMulti = "from EntityDemo where name like ?1 ";
            Query queryMulti = em.createQuery(jpqlMulti);

            //对占位符赋值，从1开始
            querySingle.setParameter(1, "小明");
            //查询并得到返回结果
            Object object = querySingle.getSingleResult(); // 得到唯一的结果集对象，Hibernate底层实现：线调用getResultList，在判断列表长度是不是一，不是的的话报错；
            System.out.println(object);

            queryMulti.setParameter(1, "小%");
            List<EntityDemo> results = queryMulti.getResultList();
            System.out.println(results);

            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }

        /**
         * DESC 倒叙查询
         */
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            // 创建query对象
            String jpql = "from EntityDemo order by id desc";
            Query query = em.createQuery(jpql);
            // 查询并得到返回结果
            List list = query.getResultList(); // 得到集合返回类型
            for (Object object : list) {
                System.out.println(object);
            }
            tx.commit();
        } catch (Exception e) {
            // 回滚事务
            tx.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            em.close();
        }


        /**
         * Count 统计查询
         */
        try {
            //获取实体管理对象
            em = JPAUtil.getEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            tx.begin();
            // 查询全部客户
            // 1.创建query对象
            String jpql = "select count(id) from EntityDemo where gender = '女'";
            Query query = em.createQuery(jpql);
            // 2.查询并得到返回结果
            Object count = query.getSingleResult(); // 得到集合返回类型
            System.out.println(count);
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
