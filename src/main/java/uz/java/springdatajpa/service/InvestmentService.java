package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.java.springdatajpa.dto.InvestmentDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.Investment;
import uz.java.springdatajpa.repository.ForecastRepository;
import uz.java.springdatajpa.repository.InvestmentRepository;
import uz.java.springdatajpa.service.mapper.InvestmentMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvestmentService {
    private final ForecastRepository forecastRepository;
    private final InvestmentMapper investmentMapper;
    private final InvestmentRepository investmentRepository;

    public ResponseDto<InvestmentDto> invest(InvestmentDto investmentDto) {
        try {
            Investment investment = investmentMapper.toEntity(investmentDto);
            investment.setCreatedAt(new Date());
            investmentRepository.save(investment);
            return ResponseDto.<InvestmentDto>builder()
                    .success(true)
                    .message("Investment muvoffaqiyatli saqlandi!")
                    .data(investmentDto)
                    .build();
        }catch (Exception e){
            log.error("Investmentni saqlashda xatolik: {}", e.getMessage());
            return ResponseDto.<InvestmentDto>builder()
                    .success(false)
                    .message("Investment Saqlanmadi!")
                    .code(2)
                    .build();
        }
    }

    public ResponseDto<InvestmentDto> getById(Integer id) {
        Optional<Investment> investment = investmentRepository.findById(id);
        if (investment.isEmpty()){
            return ResponseDto.<InvestmentDto>builder()
                    .message("Investitsiya topilmadi!")
                    .code(-1)
                    .success(false)
                    .build();
        }
        InvestmentDto investmentDto = investmentMapper.toDto(investment.get());
        return ResponseDto.<InvestmentDto>builder()
                .success(true)
                .message("OK")
                .data(investmentDto)
                .build();
    }

    public ResponseDto<Page<InvestmentDto>> getAllByUserId(Integer userId, Integer size, Integer page){
        return ResponseDto.<Page<InvestmentDto>>builder()
                .data(investmentRepository.findAllByAccount_users_id(userId, PageRequest.of(page, size))
                        .map(investmentMapper::toDto))
                .success(true)
                .build();
    }

    public ResponseDto<String> prognoz(InvestmentDto dto) {
        return forecastRepository.findFirstByDuration(dto.getDuration())
                .map(f -> ResponseDto.<String>builder()
                            .success(true)
                            .data(String.format("%.2f", dto.getAmount() * f.getPercent() / 100))
                            .message("OK")
                            .build())
                        .orElse(ResponseDto.<String>builder()
                            .message("There is no forecast for this duration")
                            .code(2)
                            .build());
    }

    public ResponseDto<List<InvestmentDto>> topInvestments() {
        List<InvestmentDto> investments = investmentRepository.getMaxInvestmentByMonths()
                .stream()
                .map(investmentMapper::toDtoWithAccount)
                .toList();

        return ResponseDto.<List<InvestmentDto>>builder()
                .message("OK")
                .success(true)
                .data(investments)
                .build();
    }

    public ResponseDto<List<InvestmentDto>> getAllInvestmentsByAmount(Integer max, Integer min) {
        List<InvestmentDto> investments = investmentRepository.findAllByAmount(max, min, PageRequest.of(0, 10))
                .stream()
                .map(investmentMapper::toDtoWithAccount)
                .toList();

        return ResponseDto.<List<InvestmentDto>>builder()
                .message("OK")
                .data(investments)
                .success(true)
                .build();
    }

    public ResponseDto<List<InvestmentDto>> getAllByFirstName(String firstName) {
        List<Investment> investments = investmentRepository.findAllByFirstName2(firstName);

        if (investments.isEmpty()){
            return ResponseDto.<List<InvestmentDto>>builder()
                    .message("Investment of user with firstname " + firstName + " is not found")
                    .code(-1)
                    .build();
        }

        return ResponseDto.<List<InvestmentDto>>builder()
                .success(true)
                .message("OK")
                .data(investments.stream().map(investmentMapper::toDtoWithAccount).toList())
                .build();
    }
}
