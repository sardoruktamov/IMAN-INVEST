package uz.java.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.CurrencyDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.exceptions.DataNotFoundException;
import uz.java.springdatajpa.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyService;

    @GetMapping("by-short-name")
    public ResponseDto<CurrencyDto> getByName(@RequestParam String cur) throws DataNotFoundException {
        return currencyService.getCurrencyInfoByShortName(cur);
    }

    @GetMapping("/{id}")
    public ResponseDto<CurrencyDto> getCurrencyById(@PathVariable Integer id){
        return currencyService.findById(id);
    }

    @PostMapping()
    public ResponseDto<CurrencyDto> addNewCurrency(@RequestBody CurrencyDto currencyDto){
        return currencyService.addNewCurrency(currencyDto);
    }
    // changes
}
//DTO - Data Transfer Object
//USD - United States Dollar