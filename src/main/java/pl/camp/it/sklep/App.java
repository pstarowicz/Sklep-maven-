package pl.camp.it.sklep;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.camp.it.sklep.configuration.AppConfiguration;
import pl.camp.it.sklep.core.Engine;

public class App {
    public static void main(String[] args) {
        ApplicationContext pudelko = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Engine engine = pudelko.getBean(Engine.class);
        engine.start();
    }
}
