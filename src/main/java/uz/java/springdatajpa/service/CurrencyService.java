package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.Currency;
import uz.java.springdatajpa.model.Investment;
import uz.java.springdatajpa.repository.CurrencyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public ResponseDto<Currency> create(Currency currency) {
        try {
            currencyRepository.save(currency);
            return ResponseDto.<Currency>builder()
                    .message("Currency successful created!")
                    .success(true)
                    .data(currency)
                    .build();
        } catch (Exception e) {
            log.error("No successful Currency was created!");
            return ResponseDto.<Currency>builder()
                    .message("the currency was not saved!")
                    .success(false)
                    .code(2)
                    .build();

        }
    }

    public ResponseDto<Currency> get(Integer id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UsersDto user){
            log.info("Currency GET by ID => ID = {}, user = {}", id, user.getUsername());
        }

        return currencyRepository.findById(id).map(currency -> ResponseDto.<Currency>builder()
                        .success(true)
                        .message("OK")
                        .data(currency)
                        .code(0)
                        .build())
                .orElseGet(() -> ResponseDto.<Currency>builder()
                        .message("Currency not found in" + id + "- id")
                        .success(false)
                        .code(-1)
                        .build());
    }

    public ResponseDto<Currency> update(Integer id, Currency currency) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<Currency>builder()
                    .success(false)
                    .message("Currency not found in " + id + "- id")
                    .code(-1)
                    .build();
        }
        //TODO:Continue with the currency update method

        return null;
    }

    public ResponseDto<Currency> delete(Integer id) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<Currency>builder()
                    .message("Currency not found in" + id + "- id")
                    .success(false)
                    .code(-1)
                    .build();
        }

        currencyRepository.delete(optional.get());


        return ResponseDto.<Currency>builder()
                .success(true)
                .message("Currency successful deleted!")
                .data(optional.get())
                .code(0)
                .build();
    }
}
