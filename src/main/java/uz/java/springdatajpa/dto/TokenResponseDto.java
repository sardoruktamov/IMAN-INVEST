package uz.java.springdatajpa.dto;

import lombok.*;

/**
 * @author a.ergashev
 * Date: 24.04.2023
 * Time: 21:45
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
