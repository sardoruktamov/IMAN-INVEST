package uz.java.springdatajpa.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Getter
@Setter
public class TestComponent {

    @PostConstruct
    public void init(){
        System.out.println("TestComponent bean is created successfully!");
        num = 0;
    }

    @PreDestroy
    public void destroy(){
        System.out.println("TestComponent bean is destroyed");
    }

    private Integer num;
    private String name;

    public void say(){
        System.out.println("Hello from component bean");
    }

}
