package uz.java.springdatajpa.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = ("user_goals"))
@Getter
@Setter
public class UserGoals {

    @Id
    @GeneratedValue(generator = "user_goals_seq")
    @SequenceGenerator(name = "user_goals_seq", sequenceName = "user_goals_id_seq", allocationSize = 1)
    private Integer id;

    private Integer userId;
    private Integer goalId;
    private Double amount;
    private Date estimatedDate;
    private Date createdAt;
    private Double monthlyInvestment;
}
