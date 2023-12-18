package uz.java.springdatajpa.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    private Integer id;
    private String currencyName;
    private String currencyShortName;
    private Double difference;
    private String changedDate;
}
