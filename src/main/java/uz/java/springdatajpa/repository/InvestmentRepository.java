package uz.java.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.java.springdatajpa.model.Investment;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {

    Page<Investment> findAllByAccount_users_id(Integer id, Pageable pageable);

    @Query(value = "select i from Investment i " +
            "where i.amount >= :min and i.amount <= :max")
    Page<Investment> findAllByAmount(@Param("max")Integer max, @Param("min") Integer min, Pageable pageable);

    @Query("select i from Investment i where i.account.users.firstName = :firstName")
    List<Investment> findAllByFirstName(@Param("firstName") String firstName);

    @Query(name = "findInvestmentByUsersFirstName")
    List<Investment> findAllByFirstName2(@Param("firstName") String firstName);

    @Query(value = "select t.*, u.first_name, u.last_name from investment t\n" +
            "left join account a on t.account_id = a.id\n" +
            "left join users u on a.user_id = u.id\n" +
            "where \n" +
            "(SUBSTRING(cast(t.created_at as text), 1, 7), t.amount)\n" +
            "in (select SUBSTRING(cast(i.created_at as text), 1, 7) as month, max(i.amount) as amount from investment i\n" +
            "group by SUBSTRING(cast(i.created_at as text), 1, 7))",
    nativeQuery = true)
    List<Investment> getMaxInvestmentByMonths();
}
//HQL - Hibernate Query Language
//Named queries
//NativeQuery - Qaysi databasega bog'lanib turgan bo'lsa, o'sha database tili