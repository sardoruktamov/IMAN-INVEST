package uz.java.springdatajpa.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

    @ManyToOne //Many accounts to One currency
    private Currency currency;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    private Integer amount = 0;

    @OneToMany(mappedBy = "account")
    private Set<Investment> investments;
}
//Database
//  |
//JDBC - Java Database Connectivity
//ORM - object relational mapping
//JPA - Java Persistence API - specification
//Hibernate, MyBatis, Eclipse-Link...
//Spring Data JPA - Spring framework module
