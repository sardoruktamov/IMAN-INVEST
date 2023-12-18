package uz.java.springdatajpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author a.ergashev
 * Date: 26.04.2023
 * Time: 22:25
 */
@RedisHash(timeToLive = 5 * 60)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TempPasswordReset {
    @Id
    private String id;
    private String email;
}
