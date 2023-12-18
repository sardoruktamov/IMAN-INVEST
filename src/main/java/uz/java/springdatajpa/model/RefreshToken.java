package uz.java.springdatajpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import uz.java.springdatajpa.dto.UsersDto;

/**
 * @author a.ergashev
 * Date: 24.04.2023
 * Time: 21:51
 */
@RedisHash(timeToLive = 60 * 60 * 24)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;
    private UsersDto user;
}
