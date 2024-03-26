package com.example.library.controller.book;

import com.example.library.controller.book.dto.CreateBookDto;
import com.example.library.controller.book.dto.CreateBookResponseDto;
import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@PreAuthorize("hasRole('ADMIN')"  )
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')"  )
    public List<GetBookDto> getAllBooks(){
        return  bookService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')"  )
    public GetBookDto getOne(@PathVariable long id){
        return  bookService.getOne(id);
    }

    @PostMapping()
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody CreateBookDto book){
        var  newBook  = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}