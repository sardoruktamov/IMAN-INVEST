package uz.java.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.java.springdatajpa.model.RefreshToken;

/**
 * @author a.ergashev
 * Date: 24.04.2023
 * Time: 21:53
 */
@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
