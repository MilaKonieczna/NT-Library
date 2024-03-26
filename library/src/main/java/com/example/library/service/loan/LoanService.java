package com.example.library.service.loan;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.loan.dto.CreateLoanDto;
import com.example.library.controller.loan.dto.CreateLoanResponseDto;
import com.example.library.controller.loan.dto.GetLoanResponseDto;
import com.example.library.controller.user.dto.GetUserDto;
import com.example.library.infrastructure.entity.BookEntity;
import com.example.library.infrastructure.entity.LoanEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.infrastructure.repository.LoanRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.loan.error.LoanNotFound;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public CreateLoanResponseDto create(CreateLoanDto loan){
        UserEntity user = userRepository.findById(loan.getUserId()).orElseThrow(() -> UserNotFound.create(loan.getUserId()));

        BookEntity book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> BookNotFound.create(loan.getBookId()));
        LoanEntity loanEntity = new LoanEntity();

        loanEntity.setUser(user);
        loanEntity.setBook(book);
        loanEntity.setLoanDate(new Date(System.currentTimeMillis()));
        loanEntity.setDueDate(loan.getDueDate());
        return new CreateLoanResponseDto(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getDueDate(), loanEntity.getUser().getId(), loanEntity.getBook().getId());
    }

    public GetLoanResponseDto  getOneById(long id){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(() -> LoanNotFound.create(id));
        return mapLoan(loan);
    }

    public List<GetLoanResponseDto> getAll(){
        List<LoanEntity> loan = loanRepository.findAll();
        return loan.stream().map(this::mapLoan).collect(Collectors.toList());
    }


    public GetLoanResponseDto  mapLoan(LoanEntity loan){
        GetUserDto user =  new GetUserDto(loan.getUser().getId(), loan.getUser().getName(), loan.getUser().getLastName(), loan.getUser().getEmail());
        GetBookDto book =  new GetBookDto(
                loan.getBook().getId(),
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getBook().getAuthor(),
                loan.getBook().getPublisher(),
                loan.getBook().getPublicationYear(),
                loan.getBook().getAvailableCopies()>0);
        return  new GetLoanResponseDto(loan.getId(), loan.getLoanDate(), loan.getDueDate(), user, book);
    }

}
