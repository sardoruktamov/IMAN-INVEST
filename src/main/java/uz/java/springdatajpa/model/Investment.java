package uz.java.springdatajpa.model;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "findInvestmentByUsersFirstName",
                query = "select i from Investment i where i.account.users.firstName = :firstName")}
)
public class Investment {

    @Id
    @GeneratedValue(generator = "investment_id_seq")
    @SequenceGenerator(name = "investment_id_seq", sequenceName = "investment_id_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    private Account account;

    private Double amount;
    private Date createdAt;
    private Integer userGoalId;
    private Integer duration;
}
