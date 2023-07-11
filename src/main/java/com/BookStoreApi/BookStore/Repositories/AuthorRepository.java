package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}