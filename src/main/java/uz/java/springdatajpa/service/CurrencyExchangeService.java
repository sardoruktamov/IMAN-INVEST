package uz.java.springdatajpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.CurrencyDto;
import uz.java.springdatajpa.exceptions.DataNotFoundException;
import uz.java.springdatajpa.model.Currency;
import uz.java.springdatajpa.repository.CurrencyRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyDto currencyInfo(String cur){
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setChangedDate(new Date());
        switch (cur) {
            case "USD" -> {
                currencyDto.setId(1);
                currencyDto.setCurrencyName("United States Dollars");
                currencyDto.setDifference(11230D);
            }
            case "RUB" -> {
                currencyDto.setId(2);
                currencyDto.setCurrencyName("Russian Ruble");
                currencyDto.setDifference(182.62);
            }
        }

        return currencyDto;
    }

    public String addNewCurrency(String currencyName, String currencyShortname, Double difference) {
        Currency currency = new Currency();

        currency.setName(currencyName);
        currency.setShortName(currencyShortname);
        currency.setDifference(difference);

        try {
            currencyRepository.save(currency);
            return "Saved successfully!";
        }catch (Exception e){
            e.printStackTrace();
            return "Error while saving currency data: " + e.getMessage();
        }
    }

    public CurrencyDto getCurrencyInfoByShortName(String shortName) throws DataNotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.findFirstByShortName(shortName);

        if (currencyOptional.isEmpty()){
            throw new DataNotFoundException(String.format("Data with shortname %s is not found", shortName));
        }

        Currency currency = currencyOptional.get();
        return new CurrencyDto(currency.getId(),
                currency.getName(),
                currency.getDifference(),
                currency.getChangeDate());
    }
}
