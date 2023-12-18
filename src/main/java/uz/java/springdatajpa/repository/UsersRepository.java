package uz.java.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.springdatajpa.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByUsername(String username);
    boolean existsByIdAndIsActive(Integer id, short isActive);

    Optional<Users> findByIdAndIsActive(Integer id, short isActive);
    Optional<Users> findFirstByUsernameAndIsActive(String username, short isActive);
    Optional<Users> findByEmail(String email);

    @Query("select u from Users u where " +
            "coalesce(:username, u.username) = u.username " +
            "and coalesce(:firstName, u.firstName) = u.firstName " +
            "and coalesce(:lastName, u.lastName) = u.lastName " + //=> u.lastName = u.lastName
            "and coalesce(:id, u.id) = u.id " +
            "and coalesce(:pinfl, u.pinfl) = u.pinfl " +
            "and coalesce(:phoneNumber, u.phoneNumber) = u.phoneNumber " +
            "and u.isActive = 1") //...
    Page<Users> findByParams(@Param("username") String username,
                             @Param("firstName") String firstName,
                             @Param("lastName") String lastName,
                             @Param("id") Integer id,
                             @Param("pinfl") Long pinfl,
                             @Param("phoneNumber") String phoneNumber,
                             Pageable pageable);
}