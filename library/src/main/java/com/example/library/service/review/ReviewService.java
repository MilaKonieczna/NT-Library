package com.example.library.service.review;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.review.dto.CreateReviewDto;
import com.example.library.controller.review.dto.CreateReviewResponseDto;
import com.example.library.controller.review.dto.GetReviewDto;
import com.example.library.controller.user.dto.GetUserDto;
import com.example.library.infrastructure.entity.BookEntity;
import com.example.library.infrastructure.entity.ReviewEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.infrastructure.repository.ReviewRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.review.error.ReviewNotFound;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }
    public CreateReviewResponseDto create(CreateReviewDto review){
        UserEntity user = userRepository.findById(review.getUserId()).orElseThrow(() -> UserNotFound.create(review.getUserId()));

        BookEntity book = bookRepository.findById(review.getBookId()).orElseThrow(() -> BookNotFound.create(review.getBookId()));
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setUser(user);
        reviewEntity.setBook(book);
        reviewEntity.setReviewDate(new Date(System.currentTimeMillis()));
        reviewEntity.setRating(review.getRating());
        reviewEntity.setComment(review.getComment());
        return new CreateReviewResponseDto(reviewEntity.getId(), reviewEntity.getRating(), reviewEntity.getComment(), reviewEntity.getUser().getId(), reviewEntity.getBook().getId());
    }

    public GetReviewDto getOneById(long id){
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> ReviewNotFound.create(id));
        return mapReview(review);
    }

    public List<GetReviewDto> getAll(){
        List<ReviewEntity> review = reviewRepository.findAll();
        return review.stream().map(this::mapReview).collect(Collectors.toList());
    }


    public GetReviewDto  mapReview(ReviewEntity review){
        GetUserDto user =  new GetUserDto(review.getUser().getId(), review.getUser().getName(), review.getUser().getLastName(), review.getUser().getEmail());
        GetBookDto book =  new GetBookDto(
                review.getBook().getId(),
                review.getBook().getIsbn(),
                review.getBook().getTitle(),
                review.getBook().getAuthor(),
                review.getBook().getPublisher(),
                review.getBook().getPublicationYear(),
                review.getBook().getAvailableCopies()>0);
        return  new GetReviewDto(review.getId(), review.getRating(), review.getComment(), user, book);
    }

}
