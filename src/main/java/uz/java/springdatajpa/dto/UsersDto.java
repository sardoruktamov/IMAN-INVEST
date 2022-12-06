package uz.java.springdatajpa.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class UsersDto {


    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String passport;
    private Long pinfl;
    private String username;
    private String password;
    private String imageUrl;
    private String phoneNumber;
    private String email;
}
