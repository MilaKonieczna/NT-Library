package com.example.library.service.review;

import com.example.library.controller.book.dto.GetBookDto;
import com.example.library.controller.review.dto.CreateReviewDto;
import com.example.library.controller.review.dto.CreateReviewResponseDto;
import com.example.library.controller.review.dto.GetReviewDto;
import com.example.library.controller.review.dto.GetReviewsPageResponseDto;
import com.example.library.controller.user.dto.GetUserDto;
import com.example.library.infrastructure.entity.BookEntity;
import com.example.library.infrastructure.entity.ReviewEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.BookRepository;
import com.example.library.infrastructure.repository.ReviewRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.book.error.BookUnavailable;
import com.example.library.service.review.error.ReviewDoesntExist;
import com.example.library.service.review.error.ReviewNotFound;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @PreAuthorize("hasRole('ADMIN') or  (isAuthenticated() and this.isOwner(authentication.name, #review.userId))")
    public CreateReviewResponseDto create(CreateReviewDto review){
        UserEntity user = userRepository.findById(review.getUserId()).orElseThrow(() -> UserNotFound.createWithId(review.getUserId()));

        BookEntity book = bookRepository.findById(review.getBookId()).orElseThrow(() -> BookNotFound.create(review.getBookId()));

        if(book.getAvailableCopies() == 0){
            throw BookUnavailable.create(book.getId());
        }

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUser(user);
        reviewEntity.setBook(book);
        reviewEntity.setReviewDate(LocalDate.now());
        reviewEntity.setRating(review.getRating());
        reviewEntity.setComment(review.getComment());
        reviewRepository.save(reviewEntity);
        return new CreateReviewResponseDto(reviewEntity.getId(), reviewEntity.getReviewDate(), reviewEntity.getRating(), reviewEntity.getComment(),reviewEntity.getUser().getId(), reviewEntity.getBook().getId());
    }
    public GetReviewDto getOneById(long id) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow(() -> ReviewNotFound.create(id));
        return mapReview(review);
    }

    public GetReviewsPageResponseDto getAll(Long userId, int page, int size) {
        Page<ReviewEntity> reviewPages;

        Pageable pageable = PageRequest.of(page, size);
        if(userId == null){
            reviewPages = reviewRepository.findAll(pageable);
        }else {
            reviewPages = reviewRepository.findByUserId(userId, pageable);
        }
        List<GetReviewDto> review = reviewPages.getContent().stream().map(this::mapReview).toList();
        return new GetReviewsPageResponseDto(review, reviewPages.getNumber(), reviewPages.getTotalElements(), reviewPages.getTotalPages(), reviewPages.hasNext());
    }
    public GetReviewDto mapReview(ReviewEntity review) {
        GetUserDto user = new GetUserDto(review.getUser().getId(), review.getUser().getName(), review.getUser().getLastName(), review.getUser().getEmail());
        GetBookDto book = new GetBookDto(
                review.getBook().getId(),
                review.getBook().getIsbn(),
                review.getBook().getTitle(),
                review.getBook().getAuthor(),
                review.getBook().getPublisher(),
                review.getBook().getPublicationYear(),
                review.getBook().getAvailableCopies() > 0);
        return new GetReviewDto(review.getId(), review.getRating(), review.getComment(), user, book);
    }

    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and this.isOwner(authentication.name,  #userId))")
    public void delete(long id) {
        if (!reviewRepository.existsById(id)) {
            throw ReviewDoesntExist.create(id);
        }
        reviewRepository.deleteById(id);
    }

}
