package com.example.library.infrastructure.repository;

import com.example.library.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <BookEntity, Long>{

}
