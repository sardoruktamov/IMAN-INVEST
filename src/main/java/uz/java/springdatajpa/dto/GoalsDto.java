package uz.java.springdatajpa.dto;

import lombok.*;
import org.springframework.core.annotation.Order;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalsDto {

    private Integer id;

    private String name;
}
