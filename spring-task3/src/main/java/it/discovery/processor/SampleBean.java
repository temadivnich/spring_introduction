package it.discovery.processor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {

    @Init
    public void init() {
        System.out.println("Init called");
    }

    @Init
    public void init(ApplicationContext applicationContext) {
        System.out.println("Init called with context: " + applicationContext);
    }
}
