package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.java.springdatajpa.dto.ForecastDto;
import uz.java.springdatajpa.model.Forecast;

@Mapper(componentModel = "spring")
public interface ForecastMapper extends CommonMapper<ForecastDto, Forecast> {
}
