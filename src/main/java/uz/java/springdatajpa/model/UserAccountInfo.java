package uz.java.springdatajpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "user_account_info")
public class UserAccountInfo {

    @Id
    public Integer userId;
    private Integer accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String passportSerialNumber;
    private String phoneNumber;
    private Long pinfl;
    private Integer amount;
    private String currency;
}
/**
 * userId - camel-case
 * user-id - kebab case
 * user_id - snake case
 * user - lower case
 * USER - upper case

 * 1.Entity
 * 2.Repository
 * 3.Service
 * 4.Controller -> PathVariable (account-info/{userId})
 */