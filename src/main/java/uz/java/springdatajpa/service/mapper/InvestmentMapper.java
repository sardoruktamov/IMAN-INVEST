package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import uz.java.springdatajpa.dto.InvestmentDto;
import uz.java.springdatajpa.model.Investment;
import uz.java.springdatajpa.service.mapper.annotations.WithBody;
import uz.java.springdatajpa.service.mapper.annotations.WithoutBody;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static uz.java.springdatajpa.utils.DateHelper.dateFormat;
import static uz.java.springdatajpa.utils.DateHelper.fullDateFormat;

@Mapper(componentModel = "spring", imports = Date.class)
public abstract class InvestmentMapper {

    @Lazy
    @Autowired
    protected AccountMapper accountMapper;

    public Set<InvestmentDto> toDtoSet(Set<Investment> investments){
        Set<InvestmentDto> investmentDtos = new HashSet<>();

        for (Investment investment : investments){
            investmentDtos.add(toDto(investment));
        }

        return investmentDtos;
    }

    public abstract Set<Investment> toEntitySet(Set<Investment> dtos);

    @Mapping(target = "createdAt", expression = "java(new Date())")
    public abstract Investment toEntity(InvestmentDto dto);

    @WithoutBody
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "account", expression = "java(null)")
    public abstract InvestmentDto toDto(Investment investment);

    @WithBody
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "account", expression = "java(accountMapper.toDto(investment.getAccount()))")
    public abstract InvestmentDto toDtoWithAccount(Investment investment);
}
