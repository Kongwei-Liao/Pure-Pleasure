package NoUniqueBeanDefinitionException2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBean {
    @Bean
    public Userinfo getUserinfo() {
        Userinfo userinfo = new Userinfo();
        System.out.println("创建 userinfo1 = " + userinfo);
        return userinfo;
    }

    @Bean
    public Userinfo getUserinfo(String[] args) {
        Userinfo userinfo = new Userinfo();
        System.out.println("创建 userinfo1 = " + userinfo);
        return userinfo;
    }

    @Bean
    public Userinfo createtUserinfo() {
        Userinfo userinfo = new Userinfo();
        System.out.println("创建 userinfo2 = " + userinfo);
        return userinfo;
    }
}