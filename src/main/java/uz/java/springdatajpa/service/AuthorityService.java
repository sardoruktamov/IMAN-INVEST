package uz.java.springdatajpa.service;

import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.AuthorityDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.repository.AuthorityRepository;
import uz.java.springdatajpa.service.mapper.AuthorityMapper;

import java.util.List;

@Service
public record AuthorityService(AuthorityMapper authorityMapper, AuthorityRepository authorityRepository) {

    public ResponseDto<AuthorityDto> add(AuthorityDto dto){
        return ResponseDto.<AuthorityDto>builder()
                .data(authorityMapper.toDto(authorityRepository.save(authorityMapper.toEntity(dto))))
                .message("OK")
                .success(true)
                .build();
    }

    public List<AuthorityDto> getAuthoritiesByUsername (String username){
        return authorityRepository.findAllByUsername(username)
                .stream()
                .map(authorityMapper::toDto)
                .toList();
    }
}
