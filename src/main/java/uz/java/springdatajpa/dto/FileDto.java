package uz.java.springdatajpa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 5:36 AM
 */
@Getter
@Setter
public class FileDto {
    private Integer id;
    private String name;
    private String ext;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
