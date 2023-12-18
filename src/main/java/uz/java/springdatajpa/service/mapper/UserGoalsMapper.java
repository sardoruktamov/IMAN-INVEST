package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.java.springdatajpa.dto.UserGoalsDto;
import uz.java.springdatajpa.model.UserGoals;

import static uz.java.springdatajpa.utils.DateHelper.fullDateFormat;

@Mapper(componentModel = "spring")
public abstract class UserGoalsMapper {
    @Mapping(target = "estimatedDate", source = "estimatedDate", dateFormat = fullDateFormat)
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "goal", source = "goalId")
    public abstract UserGoalsDto toDto(UserGoals userGoals);

    @Mapping(target = "estimatedDate", source = "estimatedDate", dateFormat = fullDateFormat)
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "userId", source = "user")
    @Mapping(target = "goalId", source = "goal")
    public abstract UserGoals toEntity(UserGoalsDto userGoalsDto);
}
