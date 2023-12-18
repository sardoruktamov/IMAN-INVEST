package uz.java.springdatajpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author a.ergashev
 * Date: 26.04.2023
 * Time: 22:57
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {
    private String uuid;
    private String newPassword;
}
