package uz.java.springdatajpa.service;

import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.config.TestComponent;
import uz.java.springdatajpa.dto.*;
import uz.java.springdatajpa.model.*;
import uz.java.springdatajpa.repository.*;
import uz.java.springdatajpa.repository.impl.UsersRepositoryImpl;
import uz.java.springdatajpa.security.JwtUtil;
import uz.java.springdatajpa.service.mapper.UserMapper;
import uz.java.springdatajpa.utils.ReflectionUtils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserAccountInfoRepository userAccountInfoRepository;
    private final TestComponent testComponent;
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepositoryImpl usersRepositoryImpl;
    private final CurrencyRepository currencyRepository;
    private final AccountRepository accountRepository;
    private final AuthorityService authorityService;
    private final UserSessionRepository userSessionRepository;
    private final JwtUtil jwtUtil;
    private final Gson gson;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmailService emailService;
    private final TempPasswordResetRepository tempPasswordResetRepository;

    @PostConstruct
    public void init() {
        log.info("UserService bean is created");
        testComponent.setNum(testComponent.getNum() + 10);
//        testComponent.setName("testComponent name changed to 1");
    }

    @PreDestroy
    public void destroy() {
        log.info("TestComponent value from UserService class :: " + testComponent.getNum());
    }

    public ResponseDto<UserAccountInfo> getUserAccountInfoById(Integer userId) {
        testComponent.setNum(testComponent.getNum() + 10);
        log.info("TestComponent from UserService. Num = {}", testComponent.getNum());

        Optional<UserAccountInfo> userAccountInfo = userAccountInfoRepository.findById(userId);

        if (userAccountInfo.isEmpty()) {
            return ResponseDto.<UserAccountInfo>builder()
                    .success(false)
                    .message("User account info with this user ID is not found")
                    .code(-1)
                    .build();
        }

        return ResponseDto.<UserAccountInfo>builder()
                .success(true)
                .message("OK")
                .code(0)
                .data(userAccountInfo.get())
                .build();
    }

    @Transactional
    public ResponseDto<UsersDto> addUser(UsersDto usersDto) {
        String errorMessage = null;
        int errorCode = 0;

        Users users = userMapper.toEntity(usersDto);
        users.setIsActive((short) 1);

        if (users.getId() != null) {
            users.setId(null);
        }
        try {
            usersRepository.save(users);

//      1. Userning unique bo'lgan maydon qiymatlari oldindan bor yoki yo'qligi tekshiriladi.
//      kelgan unique maydonlar bo'yicha database dan bor yoki yo'qligini tekshirib kelish

//            if (usersRepository.existsByUsername(usersDto.getUsername())) {
//                return ResponseDto.<UsersDto>builder()
//                        .code(1)
//                        .message("There is already exists user with this username: " + usersDto.getUsername())
//                        .build();
//            }

          /*  emailService.sendForRecreatePassword(usersDto.getEmail(), "Welcome", "Welcome, " + usersDto.getFirstName() + ", to our platform." +
                    "We are glad that you joined us!");*/

            return ResponseDto.<UsersDto>builder()
                    .success(true)
                    .message("Successfully saved!")
                    .data(userMapper.toDto(users))
                    .build();

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException cve) {
                errorMessage = cve.getConstraintName() + " is already exists: " + ReflectionUtils.getValue(usersDto, cve.getConstraintName());
                errorCode = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error while saving user: {}", e.getMessage());
            errorMessage = "Error while saving user: " + e.getMessage();
            errorCode = 2;
        }

        return ResponseDto.<UsersDto>builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }

    public ResponseDto<UsersDto> get(Integer id) {
        Optional<Users> optional = usersRepository.findByIdAndIsActive(id, (short) 1);
        if (optional.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("User is not found + ID " + id)
                    .code(-1)
                    .success(false)
                    .build();
        }
        UsersDto dto = userMapper.toDto(optional.get());
        return ResponseDto.<UsersDto>builder()
                .message("OK")
                .code(0)
                .data(dto)
                .success(true)
                .build();
    }

    public ResponseDto<UsersDto> update(UsersDto dto, Integer id) {
        try {
            Users users = userMapper.toEntity(dto);
            users.setUpdatedAt(new Date());
            usersRepository.save(users);

            return ResponseDto.<UsersDto>builder()
                    .message("User updated ID: " + id)
                    .success(true)
                    .data(dto)
                    .build();

        } catch (Exception e) {
            log.error("Error while updating User");
            return ResponseDto.<UsersDto>builder()
                    .code(2)
                    .message("User not updated")
                    .build();
        }
    }

    public ResponseDto<UsersDto> delete(Integer id) {
        Optional<Users> optional = usersRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UsersDto>builder()
                    .message("User is not found ID: " + id)
                    .code(-1)
                    .build();
        }
        Users users = optional.get();
        users.setIsActive((short) 0);
        users.setDeletedAt(new Date());
        usersRepository.save(users);

        UsersDto dto = userMapper.toDto(users);
        return ResponseDto.<UsersDto>builder()
                .data(dto)
                .success(true)
                .message("User success deleted ")
                .build();
    }

    public ResponseDto<Page<UsersDto>> searchUsers(Map<String, String> params) {

        int page = 0, size = 10;

        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }

//        Page<UsersDto> users = usersRepository.findByParams(
//                        params.get("username"),
//                        params.get("firstName"),
//                        params.get("lastName"),
//                        params.get("id") == null ? null : Integer.parseInt(params.get("id")),
//                        params.get("pinfl") == null ? null : Long.parseLong(params.get("pinfl")),
//                        params.get("phoneNumber"),
//                        PageRequest.of(page, size))
//                .map(userMapper::toDto);

        Page<UsersDto> users = usersRepositoryImpl.searchProducts(params, size, page)
                .map(userMapper::toDto);

        if (users.isEmpty()) {
            return ResponseDto.<Page<UsersDto>>builder()
                    .code(-1)
                    .message("User with params " + params + " is not found")
                    .build();
        }

        return ResponseDto.<Page<UsersDto>>builder()
                .code(0)
                .message("OK")
                .success(true)
                .data(users)
                .build();
    }

    @Override
    public UsersDto loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findFirstByUsernameAndIsActive(username, (short) 1);

        return users.map(u -> {
                    List<AuthorityDto> authorities = authorityService.getAuthoritiesByUsername(u.getUsername());
                    UsersDto usersDto = userMapper.toDto(u);
                    usersDto.setAuthorities(authorities);
                    return usersDto;
                })
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " is not found!"));
    }

    public ResponseDto<TokenResponseDto> singIn(LoginDto loginDto) {
        UsersDto usersDto = loadUserByUsername(loginDto.getUsername());
        if (usersDto == null)
            throw new UsernameNotFoundException("User with username " + loginDto.getUsername() + " is not found!");

        String sessionId = UUID.randomUUID().toString();
        userSessionRepository.save(new UserSession(sessionId, usersDto));
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenRepository.save(new RefreshToken(refreshToken, usersDto));

        if (passwordEncoder.matches(loginDto.getPassword(), usersDto.getPassword())) {
            return ResponseDto.<TokenResponseDto>builder()
                    .success(true)
                    .message("OK")
                    .data(TokenResponseDto.builder()
                            .accessToken(jwtUtil.generateToken(sessionId))
                            .refreshToken(refreshToken)
                            .build()
                    )
                    .build();
        }

        throw new BadCredentialsException("Password is incorrect");
    }

    public ResponseDto<TokenResponseDto> refreshToken(String refreshToken) {
        Optional<RefreshToken> rToken = refreshTokenRepository.findById(refreshToken);
        if (rToken.isEmpty()) {
            throw new BadCredentialsException("Your credentials are incorrect or expired. Log in again!");
        }

        String sessionId = UUID.randomUUID().toString();
        userSessionRepository.save(new UserSession(sessionId, rToken.get().getUser()));

        return ResponseDto.<TokenResponseDto>builder()
                .success(true)
                .message("OK")
                .data(TokenResponseDto.builder()
                        .refreshToken(refreshToken)
                        .accessToken(jwtUtil.generateToken(sessionId))
                        .build())
                .build();

    }

    @Transactional
    public ResponseDto<String> resetPassword(String email) throws MessagingException, IOException {
        Optional<Users> user = usersRepository.findByEmail(email);
        if (user.isEmpty()) return ResponseDto.<String>builder()
                .code(-1)
                .message("Username with this email is not exists!")
                .build();

        String id = UUID.randomUUID().toString();
        TempPasswordReset temp = new TempPasswordReset(id, email);
        tempPasswordResetRepository.save(temp);

        emailService.sendHtmlPageToResetPassword(email, id);

        return ResponseDto.<String>builder()
                .message("OK")
                .build();
    }

    public ResponseDto confirmResetPassword(PasswordResetDto passwordResetDto) {
        Optional<TempPasswordReset> tempPasswordReset = tempPasswordResetRepository.findById(passwordResetDto.getUuid());
        if (tempPasswordReset.isEmpty()) {
            return ResponseDto.builder()
                    .message("Reset time is expired!")
                    .build();
        }

        Optional<Users> users = usersRepository.findByEmail(tempPasswordReset.get().getEmail());
        if (users.isEmpty()) return ResponseDto.builder()
                .message("User is not exists")
                .build();

        users.get().setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
        usersRepository.save(users.get());

        return ResponseDto.builder()
                .message("OK")
                .code(0)
                .success(true)
                .build();
    }
}
//@Controller @RestController @Service @Repository @Configuration @Bean @Component...
//@Configuration @Component @Bean -> @Repository -> @Service -> @Controller -> @RestController
//1. Autowired
//2. Constructor parameters
//3. setter method