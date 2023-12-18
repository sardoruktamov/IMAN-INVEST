package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.ErrorDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.dto.UserGoalsDto;
import uz.java.springdatajpa.model.UserGoals;
import uz.java.springdatajpa.repository.UserGoalsRepository;
import uz.java.springdatajpa.service.mapper.UserGoalsMapper;
import uz.java.springdatajpa.service.validator.UserGoalsValidator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserGoalsService {
    private final UserGoalsRepository userGoalsRepository;
    private final UserGoalsMapper userGoalsMapper;

    private final UserGoalsValidator userGoalsValidator;

    public ResponseDto<UserGoalsDto> getById(Integer id) {
        Optional<UserGoals> userGoals = userGoalsRepository.findById(id);
        if (userGoals.isEmpty()) {
            return ResponseDto.<UserGoalsDto>builder()
                    .message("UserGoals topilmadi")
                    .code(-1)
                    .build();
        }
        UserGoalsDto userGoalsDto = userGoalsMapper.toDto(userGoals.get());
        return ResponseDto.<UserGoalsDto>builder()
                .success(true)
                .message("OK")
                .data(userGoalsDto)
                .build();
    }

    public ResponseDto<UserGoalsDto> save(UserGoalsDto userGoalsDto) {
        try {
            UserGoals userGoals = userGoalsMapper.toEntity(userGoalsDto);
            userGoalsRepository.save(userGoals);
            return ResponseDto.<UserGoalsDto>builder()
                    .message("UserGoals muvaqqiyatli saqlandi")
                    .success(true)
                    .data(userGoalsDto)
                    .build();
        } catch (Exception e) {
            log.error("UserGoals saqlashda xatolik bo'ldi -- {}", e.getMessage());
            return ResponseDto.<UserGoalsDto>builder()
                    .message("UserGoals saqlanmadi!")
                    .code(2)
                    .build();
        }
    }

    public ResponseDto<UserGoalsDto> update(UserGoalsDto dto, Integer id) {
        try {
            List<ErrorDto> errors = userGoalsValidator.validateUserGoals(dto, id);
            if (!errors.isEmpty()) {
                return ResponseDto.<UserGoalsDto>builder()
                        .message("Validation Errors")
                        .code(1)
                        .data(dto)
                        .errors(errors)
                        .build();
            }

            UserGoals userGoals = userGoalsMapper.toEntity(dto);
            userGoals.setId(id);
            userGoalsRepository.save(userGoals);
            return ResponseDto.<UserGoalsDto>builder()
                    .message("UserGoals is updated ID: " + id)
                    .data(dto)
                    .build();
        } catch (Exception e) {
            log.error("Error with updating UserGoals -- {} ", e.getMessage());
            return ResponseDto.<UserGoalsDto>builder()
                    .message("Account is not created!")
                    .success(false)
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserGoalsDto> delete(Integer id) {
        Optional<UserGoals> dltGoal = userGoalsRepository.findById(id);
        if (dltGoal.isEmpty()) {
            return ResponseDto.<UserGoalsDto>builder()
                    .code(-1)
                    .message("UserGoals topilmadi")
                    .build();
        }
        userGoalsRepository.deleteById(id);
        UserGoalsDto userGoalsDto = userGoalsMapper.toDto(dltGoal.get());
        return ResponseDto.<UserGoalsDto>builder()
                .success(true)
                .message("Maqsad muvaffaqiyatli o'chirildi")
                .data(userGoalsDto)
                .build();
    }
}
