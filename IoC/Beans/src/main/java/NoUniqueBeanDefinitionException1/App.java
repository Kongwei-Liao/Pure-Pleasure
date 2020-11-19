package NoUniqueBeanDefinitionException1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"NoUniqueBeanDefinitionException1.xml"});
//        Userinfo userinfo = (Userinfo)context.getBean(Userinfo.class);
//        System.out.println(userinfo);
        Userinfo userinfo1 = (Userinfo)context.getBean("userinfo1");
        Userinfo userinfo2 = (Userinfo)context.getBean("userinfo2");
        System.out.println(userinfo1);
        System.out.println(userinfo2);
    }
}
