package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.Forecast;

import java.util.Optional;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {
    Optional<Forecast> findFirstByDuration(Integer duration);
}
