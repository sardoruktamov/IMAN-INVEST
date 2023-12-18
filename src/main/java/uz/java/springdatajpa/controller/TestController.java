package uz.java.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("time")
    public ResponseDto<String> getTimeFromDB(){
        return testService.time();
    }
}
