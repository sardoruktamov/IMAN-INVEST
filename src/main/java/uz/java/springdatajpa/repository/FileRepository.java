package uz.java.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.springdatajpa.model.FileModel;

import java.util.Optional;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 6:00 AM
 */
public interface FileRepository extends JpaRepository<FileModel, Integer> {
    Optional<FileModel> findByIdAndStatus(Integer id, Integer status);
}
