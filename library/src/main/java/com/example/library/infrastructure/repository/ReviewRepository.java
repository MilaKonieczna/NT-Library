package com.example.library.infrastructure.repository;

import com.example.library.infrastructure.entity.LoanEntity;
import com.example.library.infrastructure.entity.ReviewEntity;
import com.example.library.infrastructure.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByUserId(long userId);

    Page<ReviewEntity> findByUserId(long userId, Pageable pageable);
}

