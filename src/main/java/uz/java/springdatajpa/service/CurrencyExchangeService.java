package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.CurrencyDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.exceptions.DataNotFoundException;
import uz.java.springdatajpa.model.Currency;
import uz.java.springdatajpa.repository.CurrencyRepository;
import uz.java.springdatajpa.service.mapper.CurrencyMapper;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeService {

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    public ResponseDto<CurrencyDto> addNewCurrency(CurrencyDto currencyDto) {

        try {
            Currency currency = currencyMapper.toEntity(currencyDto);
            currencyRepository.save(currency);
            return ResponseDto.<CurrencyDto>builder()
                    .message("Currency muvoffaqiyatli saqlandi")
                    .success(true)
                    .data(currencyDto)
                    .build();
        }catch (Exception e){
            log.error("Currency ma`lumotini saqlashda xatolik --> {}",e.getMessage());
            return ResponseDto.<CurrencyDto>builder()
                    .message("Currency ma`lumotini saqlashda xatolik!")
                    .success(false)
                    .code(2)
                    .build();
        }
    }

    public ResponseDto<CurrencyDto> getCurrencyInfoByShortName(String shortName) throws DataNotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.findFirstByCurrencyShortName(shortName);

        if (currencyOptional.isEmpty()){
            throw new DataNotFoundException(String.format("Data with shortname %s is not found", shortName));
        }

        Currency currency = currencyOptional.get();
        return ResponseDto.<CurrencyDto>builder()
                .success(true)
                .data(currencyMapper.toDto(currency))
                .message("OK")
                .build();
    }

    public ResponseDto<CurrencyDto> findById(Integer id) {

        Optional<Currency> currency = currencyRepository.findById(id);

        if (currency.isEmpty()){
            return ResponseDto.<CurrencyDto>builder()
                    .message("Currency ma`lumoti topilmadi!")
                    .code(-1)
                    .build();
        }

        CurrencyDto currencyDto = currencyMapper.toDto(currency.get());
        return ResponseDto.<CurrencyDto>builder()
                .message("OK")
                .success(true)
                .data(currencyDto)
                .build();


    }
}
