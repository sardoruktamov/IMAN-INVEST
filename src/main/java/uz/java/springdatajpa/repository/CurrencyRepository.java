package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findFirstByShortName(String shortname);
}
