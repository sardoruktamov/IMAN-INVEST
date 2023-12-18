package uz.java.springdatajpa.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.AuthorityDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.service.AuthorityService;

@RestController
@RequestMapping("/authorities")
public record AuthorityController(AuthorityService authorityService) {

    @PostMapping("/to-user")
    public ResponseDto<AuthorityDto> addAuthToUser(@RequestBody @Valid AuthorityDto authorityDto){
        return authorityService.add(authorityDto);
    }
}
