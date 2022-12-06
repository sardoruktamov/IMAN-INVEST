package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.UsersDto;
import uz.java.springdatajpa.model.UserAccountInfo;
import uz.java.springdatajpa.model.Users;
import uz.java.springdatajpa.repository.UserAccountInfoRepository;
import uz.java.springdatajpa.repository.UsersRepository;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAccountInfoRepository userAccountInfoRepository;
    private final UsersRepository usersRepository;

    public UserAccountInfo getUserAccountInfoById(Integer userId){
        Optional<UserAccountInfo> userAccountInfo = userAccountInfoRepository.findById(userId);

        if (userAccountInfo.isEmpty()){
            return null;
        }
        return userAccountInfo.get();
    }

    public Users getUserInfo(Integer uId){
        Optional<Users> usersinfo = usersRepository.findById(uId);

        if (usersinfo.isEmpty()){
            return null;
        }

        return usersinfo.get();
    }

    public String addUser(UsersDto usersDto){
        try {
            Users users = UserMapper.toEntity(usersDto);
            usersRepository.save(users);
            return "User ma`lumoti saqlandi, User ID: " + users.getId();
        }catch (Exception e){
            log.error("Saqlash jarayonida xatolik: {}",e.getMessage());
            return "Saqlash jarayonida xatolik: " + e.getMessage();
        }
    }
}

