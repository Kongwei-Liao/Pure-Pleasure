## 10. Hibernate生命周期

Hibernate的Session类的有一些/几组的重要方法，如下图所示：

![img](http://www.yiibai.com/uploads/images/201705/0905/931170555_26701.png)

Hibernate中的一个对象存在于以下四个状态之中的一种：

- **短暂(Transient)**
- **持久(Persistent)**
- **移除(Removed)**
- **分离(Detached)**

以上几个状态在下面图中解释：

![img](http://www.yiibai.com/uploads/images/201705/0905/689170557_42799.png)

下面来看这几个状态的流转说明:

1. 当从一个实体创建一个新的Java对象时，该对象处于“短暂”状态。 Hibernate不知道它的存在，因为它独立于Hibernate的管理。
2. 如果使用方法：`get`，`load`或`find`获取实体对象，则将获得一个等同于数据库中的`1`条记录的对象。 此对象处于**Persistent**状态。 它由Hibernate管理。
3. 会话调用方法：`save`，`saveOrUpdate`和`persist`。 合并将短暂(Transient)对象置于Hibernate的管理之下，此对象转为持久化(**Persistent**)状态。 在使用的具体情况下，它向数据库插入或更新数据。
4. `Session`调用`evict(..)`或`clear()`，以便从处于Hibernate管理状态的对象处于关闭状态，并且这些对象处于分离(`Detached`)的状态。
5. 使用`update(..)`，`saveOrUpdate(..)`，`merge(..)`将有助于重新连接分离对象。 在具体情况下，它会向数据库中创建更新或插入数据。 对象转回持久化(Persistent)状态。
6. `Session`调用方法：`remove(..)`，`delete(..)`删除除记录并持久化对象。

## 11. 用Hibernate插入，更新，删除

### 11.1 - 持久化(Persistent)

当一个对像使用 `Session` 的`get()`,`load()`,`find()`方法获取关联数据时，它处于持久化(Persistent)状态。

![img](http://www.yiibai.com/uploads/images/201705/0905/538100537_54337.png)

创建一个JAVA类文件：*PersistentDemo.java*，用于演示对象的持久化(Persistent)状态。

```java
package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.entities.Department;
import com.yiibai.entities.Employee;

public class PersistentDemo {

   public static void main(String[] args) {

       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Department department = null;

       try {
           session.getTransaction().begin();

           System.out.println("- Finding Department deptNo = D10...");

           // Persistent object.
           department = DataUtils.findDepartment(session, "D10");

           System.out.println("- First change Location");


           // Changing something on Persistent object.
           department.setLocation("Chicago " + System.currentTimeMillis());

           System.out.println("- Location = " + department.getLocation());

           System.out.println("- Calling flush...");

           // Use session.flush () to actively push the changes to the DB.
           // It works for all changed Persistent objects.
           session.flush();

           System.out.println("- Flush OK");

           System.out.println("- Second change Location");

           // Change something on Persistent object
           department.setLocation("Chicago " + System.currentTimeMillis());

           // Print out location
           System.out.println("- Location = " + department.getLocation());

           System.out.println("- Calling commit...");

           // Commit
           session.getTransaction().commit();

           System.out.println("- Commit OK");
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }

       // Create the session after it had been closed earlier
       // (Cause by commit or update)
       session = factory.getCurrentSession();
       try {
           session.getTransaction().begin();

           System.out.println("- Finding Department deptNo = D10...");

           // Query lại Department D10.

           department = DataUtils.findDepartment(session, "D10");

           // Print out location
           System.out.println("- D10 Location = " + department.getLocation());

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }

}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/839140534_80005.png)

### 11.2 - 瞬态转为持久化状态

![img](http://www.yiibai.com/uploads/images/201705/0905/136100541_18091.png)

### 11.3 - 瞬态转为持久化状态：使用persist(Object)

创建一个JAVA类文件：*PersistTransientDemo.java*，用于演示对象的持久化(Persistent)状态。

```java
package com.yiibai;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;
import com.yiibai.entities.Timekeeper;

public class PersistTransientDemo {

   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

   private static Timekeeper persist_Transient(Session session, Employee emp) {

       // Note:
       // Configuring of timekeeperId
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")            
       Timekeeper tk1 = new Timekeeper();

       tk1.setEmployee(emp);
       tk1.setInOut(Timekeeper.IN);
       tk1.setDateTime(new Date());

       // Now, 'tk1' is transient object
       System.out.println("- tk1 Persistent? " + session.contains(tk1));

       System.out.println("====== CALL persist(tk).... ===========");


       // Hibernate assign value to Id of 'tk1'
       // No action to DB.
       session.persist(tk1);

       System.out
               .println("- tk1.getTimekeeperId() = " + tk1.getTimekeeperId());


       // Now 'tk1' is Persistent object.
       // But no action with DB.
       // ==> true
       System.out.println("- tk1 Persistent? " + session.contains(tk1));

       System.out.println("- Call flush..");


       // Flush data to DB.
       // Hibernate execute insert statement.
       session.flush();

       String timekeeperId = tk1.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk1.getInOut());
       System.out.println("- dateTime = " + df.format(tk1.getDateTime()));
       System.out.println();
       return tk1;
   }

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           emp = DataUtils.findEmployee(session, "E7499");

           persist_Transient(session, emp);

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }

}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/345140536_10445.png)

### 11.4 - 瞬态转为持久化状态：使用save(Object)

创建一个JAVA类文件：*SaveTransientDemo.java*，用于演示对象的持久化(Persistent)状态。

```java
package com.yiibai;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;
import com.yiibai.entities.Timekeeper;

public class SaveTransientDemo {

   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

   private static Timekeeper persist_Transient(Session session, Employee emp) {

       // See configuration of timekeeperId:
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")
       // Create an Object, Transitent state.        
       Timekeeper tk2 = new Timekeeper();

       tk2.setEmployee(emp);
       tk2.setInOut(Timekeeper.IN);
       tk2.setDateTime(new Date());

       // Now 'tk3' are state Transient.        
       System.out.println("- tk2 Persistent? " + session.contains(tk2));

       System.out.println("====== CALL save(tk).... ===========");

       // save() very similar to persist()
       // save() return ID, persist() return void.
       // Hibernate assign ID value to 'tk2', no action with DB
       // And return ID of 'tk2'.        
       Serializable id = session.save(tk2);

       System.out.println("- id = " + id);

       //
       System.out
               .println("- tk2.getTimekeeperId() = " + tk2.getTimekeeperId());


       // Now, 'tk2' has Persistent state
       // It has been managed in Session.
       // ==> true
       System.out.println("- tk2 Persistent? " + session.contains(tk2));

       System.out.println("- Call flush..");

       // To push data into the DB, call flush().
       // If not call flush() data will be pushed to the DB when calling commit().
       // Will execute insert statement.
       session.flush();

       String timekeeperId = tk2.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk2.getInOut());
       System.out.println("- dateTime = " + df.format(tk2.getDateTime()));
       System.out.println();
       return tk2;
   }

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           emp = DataUtils.findEmployee(session, "E7499");

           persist_Transient(session, emp);

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/796140538_78034.png)

### 11.5 - 瞬态转为持久化状态：使用saveOrUpdate(Object)

创建一个JAVA类文件：*SaveOrUpdateTransientDemo.java*，用于演示对象的持久化(Persistent)状态。

```java
package com.yiibai;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;
import com.yiibai.entities.Timekeeper;

public class SaveOrUpdateTransientDemo {

   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

   private static Timekeeper saveOrUpdate_Transient(Session session,
           Employee emp) {

       // See configuration of timekeeperId:
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")
       // Create an Object, Transitent state.
       Timekeeper tk3 = new Timekeeper();

       tk3.setEmployee(emp);
       tk3.setInOut(Timekeeper.IN);
       tk3.setDateTime(new Date());

       // Now 'tk3' are state Transient.
       System.out.println("- tk3 Persistent? " + session.contains(tk3));

       System.out.println("====== CALL saveOrUpdate(tk).... ===========");

       // Here Hibernate checks, 'tk3' have ID or not (timekeeperId)
       // If no, it will be assigned automatically
       session.saveOrUpdate(tk3);

       System.out
               .println("- tk3.getTimekeeperId() = " + tk3.getTimekeeperId());

       // Now 'tk3' has Persistent state
       // It has been managed in Session.
       // But no action insert, or update to DB.
       // ==> true
       System.out.println("- tk3 Persistent? " + session.contains(tk3));

       System.out.println("- Call flush..");

       // To push data into the DB, call flush().
       // If not call flush() data will be pushed to the DB when calling commit().
       // Now possible to Insert or Update DB. (!!!)
       // Depending on the ID of 'tk3' exists in the DB or not
       session.flush();

       String timekeeperId = tk3.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk3.getInOut());
       System.out.println("- dateTime = " + df.format(tk3.getDateTime()));
       System.out.println();
       return tk3;
   }

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           emp = DataUtils.findEmployee(session, "E7499");

           saveOrUpdate_Transient(session, emp);

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/767140540_99817.png)

### 11.6 - 瞬态转为持久化状态：使用merge(Object)

创建一个JAVA类文件：*MergeTransientDemo.java*，用于演示对象的持久化(Persistent)状态。

```java
package com.yiibai;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;
import com.yiibai.entities.Timekeeper;

public class MergeTransientDemo {

   private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

   private static Timekeeper saveOrUpdate_Transient(Session session,
           Employee emp) {

       // Note:
       // Configuring of timekeeperId
       // @GeneratedValue(generator = "uuid")
       // @GenericGenerator(name = "uuid", strategy = "uuid2")        
       Timekeeper tk4 = new Timekeeper();

       tk4.setEmployee(emp);
       tk4.setInOut(Timekeeper.IN);
       tk4.setDateTime(new Date());

       // Now 'tk4' Transient status.
       System.out.println("- tk4 Persistent? " + session.contains(tk4));

       System.out.println("====== CALL merge(tk).... ===========");


       // Hibernate2 has method saveOrUpdateCopy
       // Hibernate3 change saveOrUpdateCopy to merge
       // So there will be similarities between the two methods merge and copyOrUpdate
       // Here Hibernate check tk4 has ID or not
       // If not, Hibernate assign value to ID of tk4
       // Return copy of tk4.
       Timekeeper tk4Copy = (Timekeeper) session.merge(tk4);

       System.out
               .println("- tk4.getTimekeeperId() = " + tk4.getTimekeeperId());


       // Now 'tk4' still Transient state.
       // and 'tk4Copy' has Persistent status
       // No action with DB (insert or update).
       System.out.println("- tk4 Persistent? " + session.contains(tk4));

       // 'tk4Copy' has Persistent status
       // ==> true
       System.out
               .println("- tk4Copy Persistent? " + session.contains(tk4Copy));

       System.out.println("- Call flush..");


       // This time have Insert or Update to DB. (!!!)
       session.flush();

       // 'tk4' still Transitent, after flush().
       // merge(..) safer than saveOrUpdate().
       System.out.println("- tk4 Persistent? " + session.contains(tk4));

       //
       String timekeeperId = tk4.getTimekeeperId();
       System.out.println("- timekeeperId = " + timekeeperId);
       System.out.println("- inOut = " + tk4.getInOut());
       System.out.println("- dateTime = " + df.format(tk4.getDateTime()));
       System.out.println();
       return tk4;
   }

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           emp = DataUtils.findEmployee(session, "E7499");

           saveOrUpdate_Transient(session, emp);

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/815140542_53040.png)

### 11.7 - 持久化转变为分离状态

由Hibernate管理的持久化(Persistent)条件中的一个对象可以通过以下两个`Session`的方法转换为`Detached`(独立于Hibernate的管理)状态：

- `evict (Object)` - 从Hibernate管理中删除一个对象
- `clear()` - 从Hibernate管理的对象中删除所有对象。

当然，当`Session`调用顺序为：`commit()`，`close()`或`rollback()`时，当前会话已经完成。 此会话的所有`Persistence`对象将从新打开的会话中分离。

![img](http://www.yiibai.com/uploads/images/201705/0905/892100550_69590.png)

创建一个JAVA类文件：*EvictDemo.java*，用于演示对象持久化转变为分离状态。

```java
package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;

public class EvictDemo {

   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       try {
           session.getTransaction().begin();

           // This is object has Persistent status
           emp = DataUtils.findEmployee(session, "E7499");

           // ==> true
           System.out.println("- emp Persistent? " + session.contains(emp));


           // using evict() to evicts a single object from the session
           session.evict(emp);

           // Now 'emp' has Detached status
           // ==> false
           System.out.println("- emp Persistent? " + session.contains(emp));


           // All change on the 'emp' will not update
           // if not reatach 'emp' to session
           emp.setEmpNo("NEW");

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}


Java
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/541140544_88395.png)

创建一个JAVA类文件：*ClearDemo.java*，用于演示将所有对象持久化转变为分离状态。

```java
package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Department;
import com.yiibai.entities.Employee;

public class ClearDemo {


   public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session = factory.getCurrentSession();
       Employee emp = null;
       Department dept = null;
       try {
           session.getTransaction().begin();

           // It is an object has Persistent status.
           emp = DataUtils.findEmployee(session, "E7499");
           dept = DataUtils.findDepartment(session, "D10");


           // clear() evicts all the objects in the session.
           session.clear();


           // Now 'emp' & 'dept' has Detached status
           // ==> false
           System.out.println("- emp Persistent? " + session.contains(emp));
           System.out.println("- dept Persistent? " + session.contains(dept));

           // All change on the 'emp' will not update
           // if not reatach 'emp' to session
           emp.setEmpNo("NEW");

           dept = DataUtils.findDepartment(session, "D20");
           System.out.println("Dept Name = "+ dept.getDeptName());

           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
   }
}
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/760140546_64617.png)

### 11.8 - 分离状态转变为持久化状态

Hibernate管理分离的对象可以通过以下`Session`的一些方法重新附加：

- update(Object)
- saveOrUpdate(Object)
- merge(Object)
- refresh(Object)
- lock(Object)

可以在以下示例中看到这些方法的区别：

![img](http://www.yiibai.com/uploads/images/201705/0905/721100554_22650.png)

### 11.9-分离转变为持久性状态：使用update(Object)

创建一个JAVA类文件：*UpdateDetachedDemo.java*，用于演示将对象分离转变为持久性状态。

```java
package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Department;
import com.yiibai.entities.Employee;

public class UpdateDetachedDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session1 = factory.getCurrentSession();
        Employee emp = null;
        try {
            session1.getTransaction().begin();

            // This is a Persistent object.
            emp = DataUtils.findEmployee(session1, "E7499");

            // session1 was closed after a commit is called.
            session1.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session1.getTransaction().rollback();
        }

        // Open other session
        Session session2 = factory.getCurrentSession();

        try {
            session2.getTransaction().begin();

            // Check state of 'emp'
            // ==> false
            System.out.println("- emp Persistent? " + session2.contains(emp));

            System.out.println("Emp salary: " + emp.getSalary());

            emp.setSalary(emp.getSalary() + 100);

            // update (..) is only used for Detached object.
            // (Not for Transient object).
            // Use the update (emp) to bring back emp Persistent state.
            session2.update(emp);

            // Call flush
            // Update statement will be called.
            session2.flush();

            System.out.println("Emp salary after update: " + emp.getSalary());

            // session2 was closed after a commit is called.
            session2.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session2.getTransaction().rollback();
        }

    }
}


Java
```

执行上面代码，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/672140548_49743.png)

### 11.10 - 分离转变为持久性状态：使用saveOrUpdate(Object)

创建一个JAVA类文件：*SaveOrUpdateDetachedDemo.java*，用于演示将对象分离转变为持久性状态。

```java
package com.yiibai;

import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;

public class SaveOrUpdateDetachedDemo {

   public static void main(String[] args) {

       // An object Detached state.
       Employee emp = getEmployee_Detached();

       System.out.println(" - GET EMP " + emp.getEmpId());

       // Random delete or not delete Employee
       boolean delete = deleteOrNotDelete(emp.getEmpId());

       System.out.println(" - DELETE? " + delete);

       // Call saveOrUpdate for detached object.
       saveOrUpdate_test(emp);

       // After call saveOrUpdate()
       System.out.println(" - EMP ID " + emp.getEmpId());
   }


   // Return Employee object has Detached state
   private static Employee getEmployee_Detached() {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session1 = factory.getCurrentSession();
       Employee emp = null;
       try {
           session1.getTransaction().begin();

           Long maxEmpId = DataUtils.getMaxEmpId(session1);
           System.out.println(" - Max Emp ID " + maxEmpId);

           Employee emp2 = DataUtils.findEmployee(session1, "E7839");

           Long empId = maxEmpId + 1;
           emp = new Employee();
           emp.setEmpId(empId);
           emp.setEmpNo("E" + empId);

           emp.setDepartment(emp2.getDepartment());
           emp.setEmpName(emp2.getEmpName());

           emp.setHideDate(emp2.getHideDate());
           emp.setJob("Test");
           emp.setSalary(1000F);

           // emp has been managed by Hibernate
           session1.persist(emp);

           // session1 was closed after a commit is called.
           // An Employee record are insert into DB.            
           session1.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session1.getTransaction().rollback();
       }
       // Session1 closed 'emp' switch to Detached state.
       return emp;
   }

   // Random: delete or not delete.
   private static boolean deleteOrNotDelete(Long empId) {
       // A random number 0-9
       int random = new Random().nextInt(10);
       if (random < 5) {
           return false;
       }
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session2 = factory.getCurrentSession();
       try {
           session2.getTransaction().begin();
           String sql = "Delete " + Employee.class.getName() + " e "
                   + " where e.empId =:empId ";
           Query query = session2.createQuery(sql);
           query.setParameter("empId", empId);

           query.executeUpdate();

           session2.getTransaction().commit();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           session2.getTransaction().rollback();
           return false;
       }
   }

   private static void saveOrUpdate_test(Employee emp) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       // Open other session
       Session session3 = factory.getCurrentSession();

       try {
           session3.getTransaction().begin();

           // Check state of emp
           // ==> false
           System.out.println(" - emp Persistent? " + session3.contains(emp));

           System.out.println(" - Emp salary before update: "
                   + emp.getSalary());

           // Set new salary for Detached emp object.
           emp.setSalary(emp.getSalary() + 100);


           // Using saveOrUpdate(emp) to switch emp to Persistent state
           // Note: If exists object same ID in session, this method raise Exception
           //
           // Now, no action with DB.            
           session3.saveOrUpdate(emp);

           // By pushing data into the DB.
           // It will call a Insert or update statement.
           // If the record is deleted before ==> insert
           // Else ==> update.    
           session3.flush();

           System.out
                   .println(" - Emp salary after update: " + emp.getSalary());

           // session3 was closed after a commit is called.
           session3.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session3.getTransaction().rollback();
       }

   }

}


Java
```

执行上面代码，多几次运行示例错码，可以看到两种情况，`saveOrUpdate()`方法调用在数据上插入或更新。得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/120140551_55482.png)

### 11.11-分离转变为持久性状态：使用merge(Object)

`Hibernate 2`版本有`saveOrUpdateCopy(Object)`方法。从`Hibernate 3`起，它被重命名为`merge(Object)`。 因此与`saveOrUpdate()`相比，`merge()`方法有一些相似性和差异。

`merge(Object)`不会将对象置于Hibernate的管理下，而是创建一个对象的副本，而不是管理该对象。

如果调用`saveOrUpdate(aObject)`则`aObject`由Hibernate管理，并且与`aObject`具有相同的ID将会抛出异常，但是使用`merge(aObject)`时不会得到此异常。

创建一个JAVA类文件：*MergeDetachedDemo.java*，用于演示将对象分离转变为持久性状态。

```java
package com.yiibai;

import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;

public class MergeDetachedDemo {

   public static void main(String[] args) {

       // An object has Detached status
       Employee emp = getEmployee_Detached();

       System.out.println(" - GET EMP " + emp.getEmpId());

       // Random: delete or not delete the Employee by ID.
       boolean delete = deleteOrNotDelete(emp.getEmpId());

       System.out.println(" - DELETE? " + delete);

       // Call saveOrUpdate Detached object
       saveOrUpdate_test(emp);

       // After call saveOrUpdate
       // ...
       System.out.println(" - EMP ID " + emp.getEmpId());
   }


   // Method return Employee object
   // and has Detached status.
   private static Employee getEmployee_Detached() {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session1 = factory.getCurrentSession();
       Employee emp = null;
       try {
           session1.getTransaction().begin();

           Long maxEmpId = DataUtils.getMaxEmpId(session1);
           System.out.println(" - Max Emp ID " + maxEmpId);

           Employee emp2 = DataUtils.findEmployee(session1, "E7839");

           Long empId = maxEmpId + 1;
           emp = new Employee();
           emp.setEmpId(empId);
           emp.setEmpNo("E" + empId);

           emp.setDepartment(emp2.getDepartment());
           emp.setEmpName(emp2.getEmpName());

           emp.setHideDate(emp2.getHideDate());
           emp.setJob("Test");
           emp.setSalary(1000F);

           // 'emp' has Persistant state
           session1.persist(emp);


           // session1 was closed after a commit is called.
           // An Employee record are insert into DB.
           session1.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session1.getTransaction().rollback();
       }
       // session1 closed, 'emp' switched Detached state.
       return emp;
   }


   // Delete Employee by ID
   // Random: delete or not delete
   private static boolean deleteOrNotDelete(Long empId) {
       // A random number 0-9
       int random = new Random().nextInt(10);
       if (random < 5) {
           return false;
       }
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session2 = factory.getCurrentSession();
       try {
           session2.getTransaction().begin();
           String sql = "Delete " + Employee.class.getName() + " e "
                   + " where e.empId =:empId ";
           Query query = session2.createQuery(sql);
           query.setParameter("empId", empId);

           query.executeUpdate();

           session2.getTransaction().commit();
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           session2.getTransaction().rollback();
           return false;
       }
   }

   private static void saveOrUpdate_test(Employee emp) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       // Open other session
       Session session3 = factory.getCurrentSession();

       try {
           session3.getTransaction().begin();


           // The fact, 'emp' has Detached state
           // It is not managed by Hibernate.
           // Check the status of emp:
           // ==> false
           System.out.println(" - emp Persistent? " + session3.contains(emp));

           System.out.println(" - Emp salary before update: "
                   + emp.getSalary());

           // Set new salary for Detached object 'emp'
           emp.setSalary(emp.getSalary() + 100);


           // merge(emp) return empMerge, a copy of 'emp',
           // empMerge managed by Hibernate
           // 'emp' still in Detached state
           //
           // At this time there is no action regarding DB.
           Employee empMerge = (Employee) session3.merge(emp);

           // ==> false
           System.out.println(" - emp Persistent? " + session3.contains(emp));
           // ==> true
           System.out.println(" - empMerge Persistent? "
                   + session3.contains(empMerge));


           // Push data into the DB.
           // Here it is possible to create the Insert or Update on DB.
           // If the corresponding record has been deleted by someone, it insert
           // else it update
           session3.flush();

           System.out
                   .println(" - Emp salary after update: " + emp.getSalary());

           // session3 closed after a commit is called.
           session3.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session3.getTransaction().rollback();
       }

   }

}


Java
```

执行上面代码，多几次运行示例错码，可以看到两种情况，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/708150500_58695.png)

### 11.12 - 分离转变为持久性状态：使用refresh(Object)

创建一个JAVA类文件：*RefreshDetachedDemo.java*，用于演示将对象分离转变为持久性状态。

```java
package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yiibai.DataUtils;
import com.yiibai.HibernateUtils;
import com.yiibai.entities.Employee;

public class RefreshDetachedDemo {

   public static void main(String[] args) {

       // an Object with Detached status
       Employee emp = getEmployee_Detached();

       System.out.println(" - GET EMP " + emp.getEmpId());

       // Refresh Object  
       refresh_test(emp);
   }


   // Return Employee object has Detached state
   private static Employee getEmployee_Detached() {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       Session session1 = factory.getCurrentSession();
       Employee emp = null;
       try {
           session1.getTransaction().begin();

           emp = DataUtils.findEmployee(session1, "E7839");

           // session1 was closed after a commit is called.
           // An Employee record are insert into DB.
           session1.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session1.getTransaction().rollback();
       }
       // Session1 closed 'emp' switch to Detached state.
       return emp;
   }

   private static void refresh_test(Employee emp) {
       SessionFactory factory = HibernateUtils.getSessionFactory();

       // Open other session
       Session session2 = factory.getCurrentSession();

       try {
           session2.getTransaction().begin();


           // Check the status of 'emp' (Detached)
           // ==> false
           System.out.println(" - emp Persistent? " + session2.contains(emp));

           System.out.println(" - Emp salary before update: "
                   + emp.getSalary());

            // Set new salary for 'emp'.
           emp.setSalary(emp.getSalary() + 100);


           // refresh: make a query statement
           // and switch 'emp' to Persistent state
           // The changes are ignored
           session2.refresh(emp);

           // ==> true
           System.out.println(" - emp Persistent? " + session2.contains(emp));

           System.out.println(" - Emp salary after refresh: "
                   + emp.getSalary());

           session2.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session2.getTransaction().rollback();
       }
   }
}
```

执行上面代码，多几次运行示例错码，可以看到两种情况，得到以下结果 - 

![img](http://www.yiibai.com/uploads/images/201705/1005/955150503_68813.png)



//原文出自【易百教程】，商业转载请联系作者获得授权，非商业转载请保留原文链接：https://www.yiibai.com/hibernate/hibernate-quick-guide.html 