package NoUniqueBeanDefinitionException2;

import org.springframework.stereotype.Component;

@Component
public class Userinfo {
    public Userinfo() {
        System.out.println("Userinfo执行构造方法初始化对象 = " + this);
    }
}