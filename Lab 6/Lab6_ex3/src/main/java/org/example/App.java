package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(appConfig.class);

        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input();
        textEditor.save("output");
    }
}
