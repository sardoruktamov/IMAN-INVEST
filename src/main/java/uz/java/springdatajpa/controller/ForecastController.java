package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.java.springdatajpa.dto.ForecastDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.service.ForecastService;

@RestController
@RequestMapping("forecast")
@RequiredArgsConstructor
public class ForecastController {
    private final ForecastService service;

    @PostMapping
    public ResponseDto<ForecastDto> forecast(@RequestBody ForecastDto dto){
        return service.forecast(dto);
    }
}
