package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
