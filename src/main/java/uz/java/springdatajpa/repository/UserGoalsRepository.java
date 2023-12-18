package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.springdatajpa.model.UserGoals;

@Repository
public interface UserGoalsRepository extends JpaRepository<UserGoals,Integer> {
}
