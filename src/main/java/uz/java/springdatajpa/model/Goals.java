package uz.java.springdatajpa.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "goals")
@Getter
@Setter
public class Goals {

    @Id
    @GeneratedValue(generator = "goals_seq")
    @SequenceGenerator(name = "goals_seq", sequenceName = "goal_id_seq", allocationSize = 1)
    private Integer id;

    private String name;
}
