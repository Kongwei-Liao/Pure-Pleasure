package NoUniqueBeanDefinitionException2;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("NoUniqueBeanDefinitionException2");

        Userinfo userinfo = (Userinfo) context.getBean(Userinfo.class);
        System.out.println(userinfo);

        // 翻车了没有报错怎么回事 ？？

        /**
         * @Bean注解
         * 组件对象的名称为方法名
         */

    }
}