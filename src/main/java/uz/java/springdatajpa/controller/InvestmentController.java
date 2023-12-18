package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.java.springdatajpa.dto.InvestmentDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.service.InvestmentService;

import java.util.List;

@RestController
@RequestMapping("investment")
@RequiredArgsConstructor
public class InvestmentController {
    //TODO 3-guruh: Investitsiya qilinganidan keyin pullar ko'payishni boshlasin.
    // Har oyning 25-sanasida kiritilgan investitsiyalar uchun foyda hisoblanib,
    // user accountiga o'tkazilsin. @Scheduler

    private final InvestmentService investmentService;

    @PostMapping
    public ResponseDto<InvestmentDto> invest(@RequestBody InvestmentDto dto){
        return investmentService.invest(dto);
    }

    @GetMapping("/{id}")
    public ResponseDto<InvestmentDto> investmentById(@PathVariable Integer id){
        return investmentService.getById(id);
    }

    @GetMapping("by-user-id/{id}")
    public ResponseDto<Page<InvestmentDto>> getInvestmentsByUserId(@PathVariable Integer id,
                                                                   @RequestParam Integer size,
                                                                   @RequestParam Integer page){
        return investmentService.getAllByUserId(id, size, page);
    }

    @GetMapping("/by-account")
    public ResponseDto<List<InvestmentDto>> getInvestments(@RequestParam Integer max, @RequestParam Integer min){
        return investmentService.getAllInvestmentsByAmount(max, min);
    }

    @GetMapping("/by-firstname")
    public ResponseDto<List<InvestmentDto>> getInvestmentsByFirstName(@RequestParam String firstName){
        return investmentService.getAllByFirstName(firstName);
    }

    @GetMapping("prognoz")
    public ResponseDto<String> prognoz(@RequestBody InvestmentDto dto){
        return investmentService.prognoz(dto);
    }

    @GetMapping("top-investments")
    public ResponseDto<List<InvestmentDto>> topInvestments(){
        return investmentService.topInvestments();
    }
}
