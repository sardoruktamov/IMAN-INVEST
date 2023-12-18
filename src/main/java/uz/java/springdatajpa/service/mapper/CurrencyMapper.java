package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.java.springdatajpa.dto.CurrencyDto;
import uz.java.springdatajpa.model.Currency;

import static uz.java.springdatajpa.utils.DateHelper.fullDateFormat;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(target = "changedDate", source = "changedDate", dateFormat = fullDateFormat)
    Currency toEntity(CurrencyDto currencyDto);

    @Mapping(target = "changedDate", source = "changedDate", dateFormat = fullDateFormat)
    CurrencyDto toDto(Currency currency);
}
