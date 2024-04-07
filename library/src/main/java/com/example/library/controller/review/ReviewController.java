package com.example.library.controller.review;

import com.example.library.controller.review.dto.CreateReviewDto;
import com.example.library.controller.review.dto.CreateReviewResponseDto;
import com.example.library.controller.review.dto.GetReviewDto;
import com.example.library.controller.review.dto.GetReviewsPageResponseDto;
import com.example.library.service.review.ReviewService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews")
@PreAuthorize("isAuthenticated()")
@Tag(name="Review")
public class ReviewController {
    private  final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<GetReviewsPageResponseDto> getAll(@RequestParam(required = false) Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetReviewsPageResponseDto dto = reviewService.getAll(userId, page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search success "),
            @ApiResponse(responseCode = "404", description = "Search fail", content = @Content())})
    public ResponseEntity<GetReviewDto> getOneById(@PathVariable long id){
        GetReviewDto dto = reviewService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation success "),
            @ApiResponse(responseCode = "401", description = "Creation fail", content = @Content())})
    public ResponseEntity<CreateReviewResponseDto> create(@RequestBody @Validated CreateReviewDto review) {
        var newReview = reviewService.create(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Deletion success")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
