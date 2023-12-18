package uz.java.springdatajpa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.java.springdatajpa.security.JwtFilter;
import uz.java.springdatajpa.security.SecurityFilter;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableGlobalAuthentication
@EnableMethodSecurity
public class SecurityConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final JwtFilter filter;

//    @Autowired
//    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//            .jdbcAuthentication()
//                .usersByUsernameQuery("select username, password, " +
//                        "(case when is_active = 1 then true else false end) as enabled " +
//                        "from users " +
//                        "where username = ?")
//                .passwordEncoder(passwordEncoder)
//                .dataSource(dataSource);

//                .withUser("ukpilot")
//                .password(passwordEncoder.encode("ukpilot0710"))
//                .roles("ADMIN")
//                .and()
//                .withUser("Kamoliddin")
//                .password(passwordEncoder.encode("kk001"))
//                .roles("USER")
//                .and()
//            .passwordEncoder(passwordEncoder);
//    }
    //Authentication
    //UserDetails
    //UserDetailsService
    //AuthenticationManager
    //AuthenticationProvider

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user", "/user/sign-in", "/user/refresh-token").permitAll()
                .requestMatchers("/v3/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
// 1. Authorization header
// 2. Base64 decode => username:password
// 3. UsernamePasswordAuthenticationToken => ProviderManager
// 4. ProviderManager => authenticate
// 5. AuthenticationProvider(DaoAuthenticationProvider) => UserDetailsService
// 6. loadUserByUsername(username) => UserDetails
// 7. additionalAuthenticationChecks => matches(request.password, userDetails.password)
// 8. UsernamePasswordAuthenticationToken => isAuthenticated = true