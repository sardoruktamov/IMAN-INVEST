package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Currency;
import uz.java.springdatajpa.service.CurrencyService;

@RestController
@RequestMapping("/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PreAuthorize("hasAnyAuthority('admin', 'moderator')")
    @PostMapping
    public ResponseDto<Currency> createCurrency(@RequestBody Currency currency) {
        return currencyService.create(currency);
    }

    @GetMapping("/{id}")
    public ResponseDto<Currency> getCurrency(@PathVariable("id") Integer id) {
        return currencyService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<Currency> updateCurrency(@PathVariable("id") Integer id,
                                                @RequestBody Currency currency) {
        return currencyService.update(id, currency);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Currency> deleteCurrency(@PathVariable("id") Integer id) {
        return currencyService.delete(id);
    }

}
