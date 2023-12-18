package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.dto.UserGoalsDto;
import uz.java.springdatajpa.service.UserGoalsService;

@RestController
@RequestMapping("user_goals")
@RequiredArgsConstructor
public class UserGoalsController {
    private final UserGoalsService userGoalsService;

    @PostMapping
    public ResponseDto<UserGoalsDto> addUSerGoals(@RequestBody UserGoalsDto userGoalsDto){
        return userGoalsService.save(userGoalsDto);
    }

    @PutMapping("/{id}")
    public ResponseDto<UserGoalsDto> updateUserGoals(@RequestBody UserGoalsDto userGoalsDto,
                                                     @PathVariable("id") Integer id){
        return userGoalsService.update(userGoalsDto, id);
    }

    @GetMapping("{user_goals_id}")
    public ResponseDto<UserGoalsDto> getUSerGoals(@PathVariable Integer userGoalsId){
        return userGoalsService.getById(userGoalsId);
    }

    @DeleteMapping("{user_goals_id}")
    public ResponseDto<UserGoalsDto> deleteUserGoals(@PathVariable Integer userGoalsId){
        return userGoalsService.delete(userGoalsId);

    }
}
