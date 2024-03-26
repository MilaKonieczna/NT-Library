package com.example.library.service.detail;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.detail.dto.CreateDetailDto;
import com.example.library.controller.detail.dto.CreateDetailResponseDto;
import com.example.library.controller.detail.dto.GetDetailDto;
import com.example.library.infrastructure.entity.BookEntity;
import com.example.library.infrastructure.entity.DetailEntity;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.infrastructure.repository.DetailRepository;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.detail.error.DetailNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    private final BookRepository bookRepository;

    public DetailService(DetailRepository detailRepository, BookRepository bookRepository) {
        this.detailRepository = detailRepository;
        this.bookRepository = bookRepository;
    }


    public CreateDetailResponseDto create(CreateDetailDto detail){

        BookEntity book = bookRepository.findById(detail.getBookId()).orElseThrow(() -> BookNotFound.create(detail.getBookId()));
        DetailEntity detailEntity = new DetailEntity();

        detailEntity.setBook(book);
        detailEntity.setGenre(detail.getGenre());
        detailEntity.setSummary(detail.getSummary());
        detailEntity.setCoverImageURL(detail.getCoverImageURL());
        return new CreateDetailResponseDto(detailEntity.getId(), detailEntity.getGenre(), detailEntity.getSummary(), detailEntity.getCoverImageURL(), detailEntity.getBook().getId());
    }

    public GetDetailDto getOneById(long id){
        DetailEntity detail = detailRepository.findById(id).orElseThrow(() -> DetailNotFound.create(id));
        return mapDetail(detail);
    }

    public List<GetDetailDto> getAll(){
        List<DetailEntity> detail = detailRepository.findAll();
        return detail.stream().map(this::mapDetail).collect(Collectors.toList());
    }


    public GetDetailDto  mapDetail(DetailEntity detail){
        GetBookDto book =  new GetBookDto(
                detail.getBook().getId(),
                detail.getBook().getIsbn(),
                detail.getBook().getTitle(),
                detail.getBook().getAuthor(),
                detail.getBook().getPublisher(),
                detail.getBook().getPublicationYear(),
                detail.getBook().getAvailableCopies()>0);
        return  new GetDetailDto(detail.getId(), detail.getGenre(), detail.getSummary(), detail.getCoverImageURL(), book);
    }

}
