package org.sobngwi.converter.basique;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConvServExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/conv-service-app-context.xml");
        ctx.refresh();

        Contact alain = ctx.getBean("alain", Contact.class);

        System.out.println("Contact info: " + alain);
    }
}
