package uz.java.springdatajpa.service;

import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.Users;

public class UserMapper {

    public static Users toEntity(UsersDto usersDto){
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setBirthDate(usersDto.getBirthDate());
        users.setPassport(usersDto.getPassport());
        users.setPinfl(usersDto.getPinfl());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setImageUrl(usersDto.getImageUrl());
        users.setPhoneNumber(usersDto.getPhoneNumber());
        users.setEmail(usersDto.getEmail());

        return users;
    }
}
