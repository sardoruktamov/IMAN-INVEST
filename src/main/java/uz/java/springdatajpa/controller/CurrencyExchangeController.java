package uz.java.springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.CurrencyDto;
import uz.java.springdatajpa.exceptions.DataNotFoundException;
import uz.java.springdatajpa.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyService;

    @GetMapping()
    public CurrencyDto getName(@RequestParam String cur) throws DataNotFoundException {
        return currencyService.getCurrencyInfoByShortName(cur);
    }

    @PostMapping()
    public String addNewCurrency(@RequestParam String currencyShortname,
                                 @RequestParam String currencyName,
                                 @RequestParam Double difference){
        return currencyService.addNewCurrency(currencyName, currencyShortname, difference);
    }
}
//DTO - Data Transfer Object
//USD - United States Dollar