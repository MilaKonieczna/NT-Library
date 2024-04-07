package com.example.library.controller.loan;

import com.example.library.controller.loan.dto.*;
import com.example.library.service.loan.LoanService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/loans")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name="Loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<GetLoansPageResponseDto> getAll(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetLoansPageResponseDto dto = loanService.getAll(userId, page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search success "),
            @ApiResponse(responseCode = "404", description = "Search fail", content = @Content())
    })
    public ResponseEntity<GetLoanResponseDto> getOneById(@PathVariable long id) {
        GetLoanResponseDto dto = loanService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation success "),
            @ApiResponse(responseCode = "401", description = "Creation fail", content = @Content())})
    public ResponseEntity<CreateLoanResponseDto> create(@RequestBody @Validated CreateLoanDto loan) {

        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Deletion success")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

@PatchMapping("/{id}/return")
@PreAuthorize("isAuthenticated()")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update success"),
        @ApiResponse(responseCode = "400", description = "Update fail", content = @Content())})
    public ResponseEntity<ReturnLoanResponseDto> returnBook(@PathVariable long id, Authentication authentication){
        return new ResponseEntity<>(loanService.returnBook(id, authentication), HttpStatus.OK);

    }
}
