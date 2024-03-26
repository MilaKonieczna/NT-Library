package com.example.library.controller.loan;

import com.example.library.controller.loan.dto.CreateLoanDto;
import com.example.library.controller.loan.dto.CreateLoanResponseDto;
import com.example.library.controller.loan.dto.GetLoanResponseDto;
import com.example.library.service.loan.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/loans")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')"  )
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping()
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loan){
        var  newLoan  = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLoanResponseDto> getOneById(@PathVariable long id){
        GetLoanResponseDto dto = loanService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<GetLoanResponseDto>> getAll(){
        List<GetLoanResponseDto> dto = loanService.getAll();
        return new ResponseEntity<>(dto, HttpStatus. OK);    }
}
