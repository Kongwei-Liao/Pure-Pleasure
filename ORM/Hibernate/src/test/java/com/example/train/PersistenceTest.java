package com.example.train;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceTest {
    @Test
    public void test() {
        /**
         * 创建实体管理类工厂，借助Persistence的静态方法获取
         * 		其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        // createEntityManagerFactory 这里创建的是Hibernate实现的EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //创建实体管理类
        // createEntityManager这里创建的是Hibernate实现的EntityManager
        EntityManager em = factory.createEntityManager();
        //获取事务对象
        // getTransaction这里获取的是Hibernate的事务管理
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        Customer c = new Customer();
        c.setCustName("传智播客");
        //保存操作
        em.persist(c);
        //提交事务
        tx.commit();
        //释放资源
        em.close();
        factory.close();
    }

}
