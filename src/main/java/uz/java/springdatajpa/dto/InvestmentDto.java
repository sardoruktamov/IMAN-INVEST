package uz.java.springdatajpa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Getter
@Setter
public class InvestmentDto {
    private Integer id;
    private AccountDto account;
    @Min(value = 100_000, message = "Amount must be greater than 100 000 sums")
    @Max(value = 100_000_000, message = "You cannot invest more than 100 000 000 sums")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Double amount;
    private String createdAt;
    private Integer userGoalId;
    private Integer duration;
}
