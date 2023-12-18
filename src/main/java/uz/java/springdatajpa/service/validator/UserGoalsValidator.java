package uz.java.springdatajpa.service.validator;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.java.springdatajpa.dto.ErrorDto;
import uz.java.springdatajpa.dto.UserGoalsDto;
import uz.java.springdatajpa.repository.UserGoalsRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserGoalsValidator {

    private final UserGoalsRepository userGoals;

    public List<ErrorDto> validateUserGoals(UserGoalsDto userGoalsDto,Integer id){

        List<ErrorDto> errors = new ArrayList<>();

        if (userGoalsDto.getAmount() < 0){
            errors.add(new ErrorDto("amount","Value must be greater then 0"));
        }

        if(userGoalsDto.getCreatedAt() != null){
            errors.add(new ErrorDto("createdAt","Value must be null"));
        }

        if(userGoalsDto.getMonthlyInvestment() == null){
            errors.add(new ErrorDto("MonthlyInvestment","Select the monthly investment"));
        }

        return errors;
    }

}
