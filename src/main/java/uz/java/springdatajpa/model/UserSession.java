package uz.java.springdatajpa.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import uz.java.springdatajpa.dto.UsersDto;

/**
 * @author a.ergashev
 * Date: 16.04.2023
 * Time: 11:58
 */
@RedisHash(timeToLive = 60 * 3)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    private String sessionId;
    private UsersDto usersDto;
}
