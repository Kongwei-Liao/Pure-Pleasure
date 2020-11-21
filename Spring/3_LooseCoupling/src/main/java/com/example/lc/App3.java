package com.example.lc;

import com.example.lc.helper.OutputHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App3 {
    public static void main( String[] args ) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"ApplicationContext.xml"});

        OutputHelper output = (OutputHelper)context.getBean("OutputHelper");
        output.generateOutput();
    }
}
