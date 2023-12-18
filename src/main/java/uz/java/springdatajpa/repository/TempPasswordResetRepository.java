package uz.java.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import uz.java.springdatajpa.model.TempPasswordReset;

/**
 * @author a.ergashev
 * Date: 26.04.2023
 * Time: 22:27
 */
public interface TempPasswordResetRepository extends CrudRepository<TempPasswordReset, String> {
}
