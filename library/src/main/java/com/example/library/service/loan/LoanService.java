package com.example.library.service.loan;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.loan.dto.*;
import com.example.library.controller.user.dto.GetUserDto;
import com.example.library.infrastructure.entity.BookEntity;
import com.example.library.infrastructure.entity.LoanEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.AuthRepository;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.infrastructure.repository.LoanRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.auth.OwnerService;
import com.example.library.service.auth.error.Forbidden;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.book.error.BookUnavailable;
import com.example.library.service.loan.error.LoanDoesntExist;
import com.example.library.service.loan.error.LoanNotFound;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LoanService extends OwnerService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, AuthRepository authRepository) {
        super(authRepository);
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
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

    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and this.isOwner(authentication.name,  #userId))")
    public GetLoansPageResponseDto getAll(Long userId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<LoanEntity> loanPages;
        if(userId == null){
            loanPages = loanRepository.findAll(pageable);
        }else {
            loanPages = loanRepository.findByUserId(userId, pageable);
        }
        List<GetLoanResponseDto> loans =  loanPages.getContent().stream().map(this::mapLoan).toList();
        return  new GetLoansPageResponseDto(loans, loanPages.getNumber(), loanPages.getTotalElements(), loanPages.getTotalPages(), loanPages.hasNext());
    }
    @PostAuthorize("hasRole('ADMIN') or  (isAuthenticated() and this.isOwner(authentication.name,  returnObject.user.id))")
    public GetLoanResponseDto  getOneById(long id){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(() -> LoanNotFound.create(id));
        return mapLoan(loan);
    }

    @PreAuthorize("hasRole('ADMIN') or  (isAuthenticated() and this.isOwner(authentication.name, #loan.userId))")
    public CreateLoanResponseDto create(CreateLoanDto loan){
        UserEntity user = userRepository.findById(loan.getUserId()).orElseThrow(() -> UserNotFound.createWithId(loan.getUserId()));

        BookEntity book = bookRepository.findById(loan.getBookId()).orElseThrow(() -> BookNotFound.create(loan.getBookId()));

        if(book.getAvailableCopies() == 0){
            throw BookUnavailable.create(book.getId());
        }

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setUser(user);
        loanEntity.setBook(book);
        loanEntity.setLoanDate(LocalDate.now());
        loanEntity.setDueDate(LocalDate.now().plus(4, ChronoUnit.WEEKS));
        loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(loanEntity.getId(), loanEntity.getLoanDate(), loanEntity.getDueDate(), loanEntity.getUser().getId(), loanEntity.getBook().getId());
    }
    public void delete(long id) {
        if (!loanRepository.existsById(id)) {
            throw LoanDoesntExist.create(id);
        }
        loanRepository.deleteById(id);
    }

    public ReturnLoanResponseDto returnBook(long id, Authentication authentication){
        LoanEntity loan = loanRepository.findById(id).orElseThrow(() -> LoanNotFound.create(id));

        if(!isOwnerOrAdmin(loan.getUser().getId(),  getAuthInfo(authentication))){
            throw Forbidden.create() ;
       }
        if (loan.getReturnDate() != null){
            return new ReturnLoanResponseDto(loan.getId(), loan.getLoanDate(), loan.getDueDate(), loan.getUser().getId(), loan.getBook().getId(), loan.getReturnDate());
        }
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        return new ReturnLoanResponseDto(loan.getId(), loan.getLoanDate(), loan.getDueDate(), loan.getUser().getId(), loan.getBook().getId(), loan.getReturnDate());
    }
}
