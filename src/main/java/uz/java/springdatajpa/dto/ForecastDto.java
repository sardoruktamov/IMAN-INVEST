package uz.java.springdatajpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ForecastDto {
    private Integer id;
    private Integer duration;
    private Double percent;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
