package uz.java.springdatajpa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${spring.security.secret.key}")
    private String secretKey;

    public String generateToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public JwtParser parser() {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();
    }

    public boolean isValid(String token) {
        if (!parser().isSigned(token)) return false;
        try {
            Claims body = parser()
                    .parseClaimsJws(token)
                    .getBody();

            return !StringUtils.isBlank(body.getSubject());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public <T> T getClaim(String name, String token, Class<T> type) {
        try {
            return parser()
                    .parseClaimsJws(token)
                    .getBody()
                    .get(name, type);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
