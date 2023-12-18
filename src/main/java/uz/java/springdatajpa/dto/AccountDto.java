package uz.java.springdatajpa.dto;

import lombok.*;
import uz.java.springdatajpa.model.Investment;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Integer id;

    private UsersDto user;

    private CurrencyDto currency;

    private String createdAt;

    private String updateAt;

    private String deletedAt;

    private Integer amount;

    private Set<InvestmentDto> investments;
}
