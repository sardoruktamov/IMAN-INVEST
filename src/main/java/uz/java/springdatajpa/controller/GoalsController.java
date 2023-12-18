package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.GoalsDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Goals;
import uz.java.springdatajpa.service.GoalService;

@RestController
@RequestMapping("goals")
@RequiredArgsConstructor
public class GoalsController {
    private final GoalService goalsService;
    @PostMapping
    public ResponseDto<GoalsDto> addGoal(@RequestBody GoalsDto goalsDto){
        return goalsService.save(goalsDto);
    }

    @GetMapping("/{goalsId}")
    public ResponseDto<GoalsDto> getGoal(@PathVariable Integer goalsId){
        return goalsService.getById(goalsId);
    }

    @PutMapping()
    public ResponseDto<GoalsDto> updateGoal(@RequestBody GoalsDto goalsDto){
        return goalsService.save(goalsDto);
    }

    @DeleteMapping("{goals_id}")
    public ResponseDto<GoalsDto> deleteGoal(@PathVariable Integer goalId){
        return goalsService.delete(goalId);
    }
}
