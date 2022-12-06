package uz.java.springdatajpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Investment {

    @Id
    private Integer id;
    private Integer accountId;
    private Double amount;
    private Date createdAt;
    private Integer userGoalId;
    private Integer duration;
}
