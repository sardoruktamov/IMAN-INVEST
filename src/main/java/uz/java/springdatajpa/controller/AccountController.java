package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.AccountDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Account;
import uz.java.springdatajpa.service.AccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseDto<AccountDto> getAccountById(@PathVariable("id") Integer id){
        return accountService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<AccountDto> update(@RequestBody AccountDto dto, @PathVariable("id") Integer id){
        return accountService.update(dto, id);
    }
}

