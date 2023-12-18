package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.AccountDto;
import uz.java.springdatajpa.dto.ErrorDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Account;
import uz.java.springdatajpa.repository.AccountRepository;
import uz.java.springdatajpa.service.mapper.AccountMapper;
import uz.java.springdatajpa.service.validator.AccountValidator;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    //Commit add Account Service class
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountValidator accountValidator;

    public ResponseDto<AccountDto> getById(Integer id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty() || accountOptional.get().getUsers().getIsActive() == (short) 0) {
            return ResponseDto.<AccountDto>builder()
                    .message("Account is not found for ID: " + id)
                    .code(-1)
                    .build();
        }

        AccountDto accountDto = accountMapper.toDtoWithInvestment(accountOptional.get());

        return ResponseDto.<AccountDto>builder()
                .message("OK")
                .success(true)
                .data(accountDto)
                .build();
    }

    public ResponseDto<AccountDto> update(AccountDto dto, Integer id) {
        try {
            List<ErrorDto> errors = accountValidator.validateAccount(dto, id);
            if (!errors.isEmpty()) {
                return ResponseDto.<AccountDto>builder()
                        .message("Validation Errors")
                        .code(1)
                        .data(dto)
                        .errors(errors)
                        .build();
            }

            Account account = accountMapper.toEntity(dto);
            account.setId(id);
            account.setUpdatedAt(new Date());
            accountRepository.save(account);
            return ResponseDto.<AccountDto>builder()
                    .message("Account is updated ID: " + id)
                    .data(dto)
                    .build();
        } catch (Exception e) {
            log.error("Error with updating Account -- {} ", e.getMessage());
            return ResponseDto.<AccountDto>builder()
                    .message("Account is not created")
                    .success(false)
                    .code(-1)
                    .build();
        }
    }
}
