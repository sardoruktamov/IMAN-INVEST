package uz.java.springdatajpa.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.java.springdatajpa.dto.AccountDto;
import uz.java.springdatajpa.model.Account;
import uz.java.springdatajpa.service.UserService;
import uz.java.springdatajpa.service.mapper.annotations.WithBody;
import uz.java.springdatajpa.service.mapper.annotations.WithoutBody;

import static uz.java.springdatajpa.utils.DateHelper.fullDateFormat;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected CurrencyMapper currencyMapper;

    @Autowired
    protected InvestmentMapper investmentMapper;

    @WithoutBody
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "user", expression = "java(userMapper.toDto(account.getUsers()))")
    @Mapping(target = "currency", expression = "java(currencyMapper.toDto(account.getCurrency()))")
    @Mapping(target = "investments", expression = "java(null)")
    public abstract AccountDto toDto(Account account);

    @Mapping(target = "currency", expression = "java(currencyMapper.toEntity(dto.getCurrency()))")
    @Mapping(target = "users", expression = "java(userMapper.toEntity(dto.getUser()))")
    @Mapping(target = "createdAt", dateFormat = fullDateFormat)
    public abstract Account toEntity(AccountDto dto);

    @WithBody
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = fullDateFormat)
    @Mapping(target = "user", expression = "java(userMapper.toDto(account.getUsers()))")
    @Mapping(target = "currency", expression = "java(currencyMapper.toDto(account.getCurrency()))")
    @Mapping(target = "investments", expression = "java(investmentMapper.toDtoSet(account.getInvestments()))")
    public abstract AccountDto toDtoWithInvestment(Account account);
}
