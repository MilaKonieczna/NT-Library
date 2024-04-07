package com.example.library.service.book;

import com.example.library.controller.book.dto.*;
import com.example.library.controller.book.dto.detail.DetailDto;
import com.example.library.controller.book.dto.detail.UpdateDetailDto;
import com.example.library.controller.book.dto.detail.UpdateDetailResponseDto;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.service.book.error.BookAlreadyExist;
import com.example.library.service.book.error.BookNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.library.infrastructure.entity.BookEntity;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    private GetBookDto mapBook(BookEntity book){
        return new GetBookDto(book.getId(), book.getIsbn(), book.getTitle(),book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getAvailableCopies() > 0);
    }
    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and this.isOwner(authentication.name,  #userId))")
    public GetBooksPageResponseDto getAll(int page, int size) {
        Page<BookEntity> bookPage;

        Pageable pageable = PageRequest.of(page, size);
        bookPage = bookRepository.findAll(pageable);

        List<GetBookDto> bookDto = bookPage.getContent().stream().map(this::mapBook).toList();
        return new GetBooksPageResponseDto(bookDto, bookPage.getNumber(), bookPage.getTotalElements(), bookPage.getTotalPages(), bookPage.hasNext());
    }

    public GetBookDto getOne(long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> BookNotFound.create(id));
        return mapBook(book);
    }

    public CreateBookResponseDto create(CreateBookDto book){
        Optional<BookEntity> exists = bookRepository.findByIsbn(book.getIsbn());
        if(exists.isPresent()){
            throw BookAlreadyExist.create(book.getIsbn());
        }
        var bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        bookEntity.setPublicationYear(book.getPublicationYear());
        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDto(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublicationYear(), newBook.getAvailableCopies());
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
                throw BookNotFound.create(id);
        }
        bookRepository.deleteById(id);
    }
    public PatchBookResponseDto updateBookCopies(long id, PatchBookDto dto){
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> BookNotFound.create(id));

        book.setAvailableCopies(book.getAvailableCopies() + dto.getNewCopies());

        bookRepository.save(book);
        return new PatchBookResponseDto(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getAvailableCopies());
        }
        public UpdateDetailResponseDto updateDetails(UpdateDetailDto detailsDto, long id){
            var book = bookRepository.findById(id).orElseThrow(()->BookNotFound.create(id));
            var details = new BookEntity.Details();
            details.setGenre(detailsDto.getGenre());
            details.setSummary(detailsDto.getSummary());
            details.setCover(detailsDto.getCover());
            book.setDetails(details);
            bookRepository.save(book);
            return new UpdateDetailResponseDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear(), book.getAvailableCopies(), new DetailDto(book.getDetails().getGenre()
            ,book.getDetails().getSummary(), book.getDetails().getCover()));
        }
}
