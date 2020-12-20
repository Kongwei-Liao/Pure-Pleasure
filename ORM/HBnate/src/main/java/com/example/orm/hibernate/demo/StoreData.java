package com.example.orm.hibernate.demo;

import com.example.orm.hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {
    public static void main(String[] args) {

        //creating configuration object
        Configuration cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file

        /**
         * 会话工厂(SessionFactory)
         * SessionFactory是ConnectionProvider的会话和客户端工厂。
         * 它拥有数据的二级缓存(可选)。
         * org.hibernate.SessionFactory接口提供了工厂方法来获取Session的对象。
         */
        //creating seession factory object
        SessionFactory factory=cfg.buildSessionFactory();

        /**
         * 会话(Session)Session对象提供应用程序和存储在数据库中的数据之间的接口。
         * 它是一个短生命周期的对象并包装JDBC连接。 它是事务，查询和标准的工厂。
         * 它拥有一级缓存(强制性)数据。
         * org.hibernate.Session接口提供插入，更新和删除对象的方法。
         * 它还提供了事务，查询和标准的工厂方法。
         */
        //creating session object
        Session session=factory.openSession();

        /**
         * 事务(Transaction)事务对象指定工作的原子单位,它是一个可选项。
         * org.hibernate.Transaction接口提供事务管理的方法。
         */
        //creating transaction object
        Transaction t=session.beginTransaction();

        Employee e1=new Employee();
        //e1.setId(100);
        e1.setFirstName("Max");
        e1.setLastName("Su");

        session.persist(e1);//persisting the object

        t.commit();//transaction is committed
        session.close();

        System.out.println("successfully saved");

    }
}

/**
 * 连接提供者(ConnectionProvider)
 * 它是一个JDBC连接工厂。 它从DriverManager或DataSource抽象出来的应用程序。 它是一个可选项。
 * 事务工厂(TransactionFactory)
 * 它是一个事务工厂，是一个可选项。
 */