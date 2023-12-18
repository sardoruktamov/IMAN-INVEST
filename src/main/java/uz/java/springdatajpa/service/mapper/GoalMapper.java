package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import uz.java.springdatajpa.dto.GoalsDto;
import uz.java.springdatajpa.model.Goals;


@Mapper(componentModel = "spring")
public interface GoalMapper extends CommonMapper<GoalsDto, Goals>{
}
