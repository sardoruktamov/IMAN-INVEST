package uz.java.springdatajpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authorities {
    @Id
    @GeneratedValue(generator = "auth_id_seq")
    @SequenceGenerator(sequenceName = "auth_id_seq", name = "auth_id_seq", allocationSize = 1)
    private Long id;

    private String username;
    private String authority;
}
