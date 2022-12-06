package uz.java.springdatajpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.Date;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(generator = "currency_id_seq")
    @SequenceGenerator(name = "currency_id_seq", sequenceName = "currency_id_seq", allocationSize = 1, initialValue = 10)
    private Integer id;

    private String name;
    private String shortName;
    private Double difference;
    private Date changeDate;
}
