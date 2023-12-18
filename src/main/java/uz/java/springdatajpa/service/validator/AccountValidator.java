package uz.java.springdatajpa.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.java.springdatajpa.dto.AccountDto;
import uz.java.springdatajpa.dto.ErrorDto;
import uz.java.springdatajpa.repository.AccountRepository;
import uz.java.springdatajpa.repository.CurrencyRepository;
import uz.java.springdatajpa.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountValidator {

    private final AccountRepository accountRepository;
    private final CurrencyRepository currencyRepository;
    private final UsersRepository usersRepository;

    public List<ErrorDto> validateAccount(AccountDto accountDto, Integer id) {

        List<ErrorDto> errors = new ArrayList<>();

        if (accountDto.getCurrency() == null) {
            errors.add(new ErrorDto("currency", "Value is null"));
        } else if (!currencyRepository.existsById(accountDto.getCurrency().getId())) {
            errors.add(new ErrorDto("currency", "There is no row with this in the database"));
        }

        if (accountDto.getUser() == null) {
            errors.add(new ErrorDto("user", "Value must be null"));
        } else if (!usersRepository.existsByIdAndIsActive(accountDto.getUser().getId(), (short) 1)) {
            errors.add(new ErrorDto("user", "There is no row with this in the database"));
        }

        if (accountDto.getAmount() < 0) {
            errors.add(new ErrorDto("amount", "Value must be greater then 0"));
        }

        if (accountDto.getCreatedAt() != null) {
            errors.add(new ErrorDto("createdAt", "Value must be null"));
        }

        if (accountDto.getDeletedAt() != null) {
            errors.add(new ErrorDto("deletedAt", "Value must be null"));
        }

        return errors;
    }
}
