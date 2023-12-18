package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.springdatajpa.model.Goals;

@Repository
public interface GoalsRepository extends JpaRepository<Goals,Integer> {
}
