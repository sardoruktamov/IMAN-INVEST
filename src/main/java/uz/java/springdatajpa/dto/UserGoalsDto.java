package uz.java.springdatajpa.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGoalsDto {
    private Integer id;
    private Integer user;
    private Integer goal;
    private Double amount;
    private String estimatedDate;
    private String createdAt;
    private Double monthlyInvestment;
}