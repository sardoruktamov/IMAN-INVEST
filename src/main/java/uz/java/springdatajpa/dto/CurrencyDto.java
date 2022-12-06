package uz.java.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    private Integer id;
    private String currencyName;
    private Double difference;
    private Date changedDate;
}
