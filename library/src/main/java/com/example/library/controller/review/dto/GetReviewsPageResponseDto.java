package com.example.library.controller.review.dto;

import java.util.List;

public class GetReviewsPageResponseDto {
    private List<GetReviewDto> reviews;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private boolean hasMore;

    public GetReviewsPageResponseDto(List<GetReviewDto> reviews, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.reviews = reviews;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<GetReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<GetReviewDto> reviews) {
        this.reviews = reviews;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
