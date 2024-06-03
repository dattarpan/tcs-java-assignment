package com.tcs.training.bookms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.training.bookms.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
