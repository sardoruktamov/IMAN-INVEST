package uz.java.springdatajpa.security;

import com.google.gson.Gson;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.java.springdatajpa.model.UserSession;
import uz.java.springdatajpa.repository.UserSessionRepository;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final Gson gson;
    private final UserSessionRepository userSessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isBlank(authorization) && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            if (jwtUtil.isValid(token)){
                String sub = jwtUtil.getClaim("sub", token, String.class);
                Optional<UserSession> usersDto = userSessionRepository.findById(sub);
                usersDto.ifPresent(userSession -> {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userSession.getUsersDto(),
                            null,
                            userSession.getUsersDto().getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
            }
        }

        filterChain.doFilter(request, response);
    }
}
