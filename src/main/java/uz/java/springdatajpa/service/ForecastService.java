package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.ForecastDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.repository.ForecastRepository;
import uz.java.springdatajpa.service.mapper.ForecastMapper;

@Service
@RequiredArgsConstructor
public class ForecastService {
    private final ForecastRepository repository;
    private final ForecastMapper mapper;

    public ResponseDto<ForecastDto> forecast(ForecastDto dto) {
        return ResponseDto.<ForecastDto>builder()
                .message("OK")
                .success(true)
                .data(mapper.toDto(
                        repository.save(mapper.toEntity(dto))
                ))
                .build();
    }
}
