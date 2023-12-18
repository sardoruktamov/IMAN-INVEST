package uz.java.springdatajpa.service.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.java.springdatajpa.repository.UsersRepository;

@Component
@RequiredArgsConstructor
public class UserExistsCheck implements ConstraintValidator<UserExists, String> {

    private final UsersRepository usersRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !s.isEmpty() && !usersRepository.existsByUsername(s);
    }
}
