package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.GoalsDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Goals;
import uz.java.springdatajpa.repository.GoalsRepository;
import uz.java.springdatajpa.service.mapper.GoalMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoalService {
    private final GoalsRepository goalsRepository;

    private final GoalMapper goalMapper;

    public ResponseDto<GoalsDto> getById(Integer id){
        Optional<Goals> goals = goalsRepository.findById(id);

        if (goals.isEmpty()){
            return ResponseDto.<GoalsDto>builder()
                    .code(-1)
                    .message("This goals not found in" + id + "- id")
                    .build();
        }

        GoalsDto goalsDto = goalMapper.toDto(goals.get());

        return ResponseDto.<GoalsDto>builder()
                .success(true)
                .message("OK")
                .data(goalsDto)
                .build();
    }

    public ResponseDto<GoalsDto> save(GoalsDto goalsDto){
        Goals goal = goalMapper.toEntity(goalsDto);
        try {
            goalsRepository.save(goal);
            return ResponseDto.<GoalsDto>builder()
                    .message("Maqsadingiz muvaffaqiyatli saqlandi")
                    .success(true)
                    .data(goalMapper.toDto(goal))
                    .build();
        }catch (Exception e){
            log.error("Maqsadni saqlashda xatolik bo'ldi -- {}",e.getMessage());
            return ResponseDto.<GoalsDto>builder()
                    .message("Maqsadizngizni saqlanmadi!")
                    .code(2)
                    .build();
        }
    }

    public ResponseDto<GoalsDto> delete(Integer id){
        Optional<Goals> dltGoal = goalsRepository.findById(id);
        if (dltGoal.isEmpty()){
            return ResponseDto.<GoalsDto>builder()
                    .code(-1)
                    .message("Maqsad topilmadi")
                    .build();
        }
        goalsRepository.deleteById(id);
        return ResponseDto.<GoalsDto>builder()
                .success(true)
                .message("Maqsad muvaffaqiyatli o'chirildi")
                .data(goalMapper.toDto(dltGoal.get()))
                .build();
    }
}
