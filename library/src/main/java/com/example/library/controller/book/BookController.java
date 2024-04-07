package com.example.library.controller.book;

import com.example.library.controller.book.dto.*;
import com.example.library.controller.book.dto.detail.UpdateDetailDto;
import com.example.library.controller.book.dto.detail.UpdateDetailResponseDto;
import com.example.library.service.book.BookService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name="Book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<GetBooksPageResponseDto> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetBooksPageResponseDto dto = bookService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search success"),
            @ApiResponse(responseCode = "404", description = "Search fail", content = @Content())
    })
    public GetBookDto getOne(@PathVariable long id){
        return  bookService.getOne(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation success"),
            @ApiResponse(responseCode = "400", description = "Creation fail", content = @Content())})
    public ResponseEntity<CreateBookResponseDto> create(@RequestBody @Validated CreateBookDto book){
        var  newBook  = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Deletion success")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Copies update success"),
            @ApiResponse(responseCode = "400", description = "Copies update fail", content = @Content())})
    public ResponseEntity<PatchBookResponseDto> updateBookCopies(@PathVariable long id, @RequestBody PatchBookDto dto){
        PatchBookResponseDto responseDto = bookService.updateBookCopies(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{bookId}/details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Details update success"),
            @ApiResponse(responseCode = "400", description = "Details update fail", content = @Content())})
    public ResponseEntity<UpdateDetailResponseDto> createDetails(@RequestBody UpdateDetailDto details, @PathVariable long bookId){
        var newDetails = bookService.updateDetails(details, bookId);
        return new ResponseEntity<>(newDetails, HttpStatus.OK);
    }
}