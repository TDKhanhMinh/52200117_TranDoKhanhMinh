package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve beans from the context
        Product product1a = (Product) context.getBean("product1");
        Product product1b = (Product) context.getBean("product1");
        Product product2a = (Product) context.getBean("product2");
        Product product2b = (Product) context.getBean("product2");
        Product product3a = (Product) context.getBean("product3");
        Product product3b = (Product) context.getBean("product3");


        System.out.println("Product 1A: " + product1a.getName() + " - " + product1a.getDescription() + " - $" + product1a.getPrice()  );
        System.out.println("Product 1B: " + product1b.getName() + " - " + product1b.getDescription() + " - $" + product1b.getPrice()  );
        System.out.println("Product 2A: " + product2a.getName() + " - " + product2a.getDescription() + " - $" + product2a.getPrice()  );
        System.out.println("Product 2B: " + product2b.getName() + " - " + product2b.getDescription() + " - $" + product2b.getPrice()  );
        System.out.println("Product 3A: " + product3a.getName() + " - " + product3a.getDescription() + " - $" + product3a.getPrice()   );
        System.out.println("Product 3B: " + product3b.getName() + " - " + product3b.getDescription() + " - $" + product3b.getPrice()   );

        System.out.println("product1a == product1b -> " + (product1a == product1b));
        System.out.println("product2a == product2b -> " + (product2a == product2b));
        System.out.println("product3a == product3b -> " + (product3a == product3b));
    }

}
