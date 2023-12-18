package uz.java.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.java.springdatajpa.model.UserSession;

/**
 * @author a.ergashev
 * Date: 16.04.2023
 * Time: 12:01
 */
@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, String> {
}
