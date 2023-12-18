package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.UserAccountInfo;
import uz.java.springdatajpa.model.Users;

public interface UserAccountInfoRepository extends JpaRepository<UserAccountInfo, Integer> {
}
