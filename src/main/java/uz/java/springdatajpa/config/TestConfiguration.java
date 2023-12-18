package uz.java.springdatajpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.request.RequestContextListener;
import uz.java.springdatajpa.dto.UsersDto;

@Configuration
public class TestConfiguration {

    @Bean(name = "admin")
    public UsersDto adminUser(){
        return UsersDto.builder()
                .id(1)
                .firstName("admin")
                .lastName("admin")
                .passport("pswdA12$")
                .username("admin")
                .pinfl(12412341234L)
                .passport("AB1235123")
                .build();
    }

    @Bean(name = "test")
    public UsersDto testUser(){
        return UsersDto.builder()
                .id(2)
                .firstName("test")
                .lastName("test")
                .username("testUser")
                .passport("pwd")
                .pinfl(1111111111L)
                .build();
    }

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
