package uz.java.springdatajpa.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.config.TestComponent;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.repository.TestRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class TestService {
    public TestService(TestRepository testRepository,
                       TestComponent testComponent,
                       @Qualifier("admin") UsersDto usersDto,
                       @Qualifier("test") UsersDto testUser) {
        this.testRepository = testRepository;
        this.testComponent = testComponent;
        this.usersDto = usersDto;
        this.testUser = testUser;
    }

    private final TestRepository testRepository;

    private final TestComponent testComponent;

    private final UsersDto usersDto;
    private final UsersDto testUser;

    @PostConstruct
    public void init(){
        log.info("TestService bean is created");
        testComponent.setNum(testComponent.getNum() + 10);
    }

    @PreDestroy
    public void destroy(){
        log.info("TestComponent value from TestService class :: " + testComponent.getNum());
    }


    public ResponseDto<String> time(){
        testComponent.setNum(testComponent.getNum() + 10);
        log.info("TestComponent from TestService. Num = {}", testComponent.getNum());

        testComponent.say();

        System.out.println(usersDto.getUsername());

        try {
            Instant instant = testRepository.time();

            String date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            return ResponseDto.<String>builder()
                    .data(date)
                    .message("OK")
                    .success(true)
                    .build();
        }catch (Exception e){
            log.error("Error while getting date from DB: {}", e.getMessage());

            return ResponseDto.<String>builder()
                    .code(2)
                    .message(e.getMessage())
                    .build();
        }
    }
}
