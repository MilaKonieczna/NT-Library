package com.example.library.controller.review;

import com.example.library.controller.review.dto.CreateReviewDto;
import com.example.library.controller.review.dto.CreateReviewResponseDto;
import com.example.library.controller.review.dto.GetReviewDto;
import com.example.library.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')"  )
public class ReviewController {
    private  final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @PostMapping()
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody @Validated CreateReviewDto review){
        var  newReview  = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetReviewDto> getOneById(@PathVariable long id){
        GetReviewDto dto = reviewService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<GetReviewDto>> getAll(){
        List<GetReviewDto> dto = reviewService.getAll();
        return new ResponseEntity<>(dto, HttpStatus. OK);    }
}
