package com.orm.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// 这里注解的用法都是JPA标准的
@Entity //声明实体类
@Table(name="cst_customer") //建立实体类和表的映射关系
public class Customer implements Serializable {

    /**
     * @Entity
     *                        作用：指定当前类是实体类。
     *         @Table
     *            作用：指定实体类和表之间的对应关系。
     *         	属性：
     *         		name：指定数据库表的名称
     *         @Id
     *            作用：指定当前字段是主键。
     *         @GeneratedValue
     *            作用：指定主键的生成方式。。
     *         	属性：
     *         		strategy ：指定主键生成策略。
     *         @Column
     *            作用：指定实体类属性和数据库表之间的对应关系
     *         	属性：
     *         		name：指定数据库表的列名称。
     *         		unique：是否唯一
     *         		nullable：是否可以为空
     *         		inserttable：是否可以插入
     *         		updateable：是否可以更新
     *         		columnDefinition: 定义建表时创建此列的DDL
     *         		secondaryTable: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境[重点]
     */


    /**
     * 主键生成策略
     * public enum GenerationType {
     *     TABLE,
     *     SEQUENCE,
     *     IDENTITY,
     *     AUTO;
     *
     *     private GenerationType() {
     *     }
     * }
     */
    @Id //声明当前私有属性为主键
    @GeneratedValue(strategy=GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="cust_id") //指定和表中cust_id字段的映射关系
    private Long custId;

    @Column(name="cust_name") //指定和表中cust_name字段的映射关系
    private String custName;

    @Column(name="cust_source")//指定和表中cust_source字段的映射关系
    private String custSource;

    @Column(name="cust_industry")//指定和表中cust_industry字段的映射关系
    private String custIndustry;

    @Column(name="cust_level")//指定和表中cust_level字段的映射关系
    private String custLevel;

    @ElementCollection
    private List<Address> custAddress = new ArrayList<>();

    @Column(name="cust_phone")//指定和表中cust_phone字段的映射关系
    private String custPhone;

    public Long getCustId() {
        return custId;
    }
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustSource() {
        return custSource;
    }
    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }
    public String getCustIndustry() {
        return custIndustry;
    }
    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }
    public String getCustLevel() {
        return custLevel;
    }
    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }
    public List<Address> getCustAddress() {
        return custAddress;
    }
    public String getCustPhone() {
        return custPhone;
    }
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
