package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import uz.java.springdatajpa.dto.AuthorityDto;
import uz.java.springdatajpa.model.Authorities;

@Mapper(componentModel = "spring")
public interface AuthorityMapper extends CommonMapper<AuthorityDto, Authorities> {
}
