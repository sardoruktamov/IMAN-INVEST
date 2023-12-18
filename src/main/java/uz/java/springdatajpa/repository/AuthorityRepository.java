package uz.java.springdatajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.Authorities;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authorities, Long> {
    List<Authorities> findAllByUsername(String username);
}