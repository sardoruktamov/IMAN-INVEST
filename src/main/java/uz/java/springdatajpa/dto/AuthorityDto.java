package uz.java.springdatajpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDto {
    private Long id;
    @NotBlank(message = "Value must be not null")
    private String username;
    @NotBlank(message = "Value must be not null")
    private String authority;
}
