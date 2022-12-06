package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.UserAccountInfo;
import uz.java.springdatajpa.model.Users;
import uz.java.springdatajpa.service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping("/hello-2")
    public String sayHello2(@RequestParam String name) {
        return String.format("Assalomu alaykum, %s!", name);
    }

    @GetMapping("account-info/{userId}")
    public UserAccountInfo userAccountInfo(@PathVariable Integer userId){
        return userService.getUserAccountInfoById(userId);
    }

    @GetMapping("/user-info/{uId}")
    public Users userInfo(@PathVariable Integer uId){
        return userService.getUserInfo(uId);
    }

    @PostMapping
    public String addUser(@RequestBody UsersDto usersDto){
        return userService.addUser(usersDto);
    }
}

//http://localhost:8081/user/account-info/3