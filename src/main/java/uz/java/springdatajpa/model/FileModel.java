package uz.java.springdatajpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 5:37 AM
 */
@Entity
@Getter
@Setter
public class FileModel {
    @Id
    @SequenceGenerator(name = "file_id_seq", sequenceName = "file_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "file_id_seq")
    private Integer id;
    private String name;
    private String ext;
    private String path;
    private Integer status;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
}
