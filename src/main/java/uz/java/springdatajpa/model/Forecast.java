package uz.java.springdatajpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "forecast",
        uniqueConstraints = @UniqueConstraint(name = "investmen_duration", columnNames = "duration"),
        indexes = @Index(name = "duration_ix", columnList = "duration")
)
public class Forecast {
    @Id
    @GeneratedValue(generator = "forecastId")
    @SequenceGenerator(name = "forecastId", sequenceName = "forecast_id_seq", allocationSize = 1)
    private Integer id;
    private Integer duration;
    private Double percent;
    @Column(columnDefinition = "date default now()")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(columnDefinition = "date default now()")
    @CreatedDate
    private LocalDateTime createdAt;
}
