package uz.java.springdatajpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Lazy;
import uz.java.springdatajpa.service.TestService;
import java.sql.Timestamp;
import java.time.Instant;


@Repository
public class TestRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private TestService testService;

    public Instant time(){
        return (Instant) entityManager.createNativeQuery("select now()").getSingleResult();
    }
}
