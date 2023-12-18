package uz.java.springdatajpa.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.service.UserService;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        //logics...
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isBlank(authorization) && authorization.startsWith("Basic ")) {
            String usernameAndPasswordBase64 = authorization.substring(6);
            String usernameAndPassword = new String(Base64.getDecoder().decode(usernameAndPasswordBase64));
            String username = usernameAndPassword.substring(0, usernameAndPassword.indexOf(":"));
            String password = usernameAndPassword.substring(usernameAndPassword.indexOf(":") + 1);
            UsersDto usersDto = userService.loadUserByUsername(username);
            if (passwordEncoder.matches(password, usersDto.getPassword())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usersDto,
                        password,
                        usersDto.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
