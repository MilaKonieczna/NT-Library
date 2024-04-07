package com.example.library.infrastructure.repository;

import com.example.library.infrastructure.entity.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByUserId(long userId);

    Page<LoanEntity> findByUserId(long userId, Pageable pageable);
}
