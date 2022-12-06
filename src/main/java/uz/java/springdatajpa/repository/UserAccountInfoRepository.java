package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.UserAccountInfo;

public interface UserAccountInfoRepository extends JpaRepository<UserAccountInfo, Integer> {
}
