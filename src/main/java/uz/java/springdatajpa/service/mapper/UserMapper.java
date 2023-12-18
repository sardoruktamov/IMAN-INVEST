package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.Users;

import static uz.java.springdatajpa.utils.DateHelper.dateFormat;
import static uz.java.springdatajpa.utils.DateHelper.fullDateFormat;

@Mapper(componentModel = "spring", imports = PasswordEncoder.class)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = dateFormat)
    public abstract UsersDto toDto(Users users);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = dateFormat)
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(usersDto.getPassword()))")
    public abstract Users toEntity(UsersDto usersDto);
}


