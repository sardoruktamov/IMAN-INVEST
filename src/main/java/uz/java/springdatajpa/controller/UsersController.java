package uz.java.springdatajpa.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.*;
import uz.java.springdatajpa.model.UserAccountInfo;
import uz.java.springdatajpa.service.UserService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    @GetMapping("account-info/{userId}")
    public ResponseDto<UserAccountInfo> userAccountInfo(@PathVariable Integer userId) {
        return userService.getUserAccountInfoById(userId);
    }

    //TODO 1-guruh: gmail bilan integratsiya. User emailiga user qo'shilganligi haqida xabar yuborish
    @PostMapping
    public ResponseDto<UsersDto> addUser(@RequestBody UsersDto usersDto){
        return userService.addUser(usersDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin', 'moderator')")
    public ResponseDto<UsersDto> getId(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseDto<UsersDto> update(@RequestBody UsersDto dto,
                                        @PathVariable("id") Integer id){
        return userService.update(dto,id);
    }

    @DeleteMapping("/{id}")
        public ResponseDto<UsersDto> delete(@PathVariable("id") Integer id){
        return userService.delete(id);
    }

    @GetMapping("search")
    public ResponseDto<Page<UsersDto>> findUsers(@RequestParam Map<String, String> params){
        return userService.searchUsers(params);
    }

    @PostMapping("/sign-in")
    public ResponseDto<TokenResponseDto> singIn(@RequestBody LoginDto loginDto){
        return userService.singIn(loginDto);
    }

    @PostMapping("refresh-token")
    public ResponseDto<TokenResponseDto> refreshToken(@RequestParam String refreshToken){
        return userService.refreshToken(refreshToken);
    }

    @PostMapping("reset-password")
    public ResponseDto resetPassword(@RequestParam String email) throws MessagingException, IOException {
        return userService.resetPassword(email);
    }

    @PostMapping("confirm-reset-password")
    public ResponseDto confirmResetPassword(@RequestBody PasswordResetDto passwordResetDto){
        return userService.confirmResetPassword(passwordResetDto);
    }
}
//http://localhost:8081/user
//http://localhost:8081/user/account-info/3
//comment for dev/Ubaydullo