package com.example.library.infrastructure.repository;

import com.example.library.infrastructure.entity.DetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<DetailEntity, Long> {
}

