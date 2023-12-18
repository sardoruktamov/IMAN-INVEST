package uz.java.springdatajpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Currency {
    @Id
    @GeneratedValue(generator = "currency_id_seq")
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", allocationSize = 1, initialValue = 10)
    private Integer id;
    private String currencyName;
    private String currencyShortName;
    private Double difference;
    private Date changedDate;
}
