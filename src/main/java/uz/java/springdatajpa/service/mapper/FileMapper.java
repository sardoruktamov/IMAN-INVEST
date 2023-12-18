package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import uz.java.springdatajpa.dto.FileDto;
import uz.java.springdatajpa.dto.FileWithBodyDto;
import uz.java.springdatajpa.model.FileModel;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 6:01 AM
 */
@Mapper(componentModel = "spring")
public interface FileMapper extends CommonMapper<FileDto, FileModel> {

    FileWithBodyDto toDtoWithData(FileModel fm);
}
