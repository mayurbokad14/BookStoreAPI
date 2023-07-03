package com.BookStoreApi.BookStore.Api.Models;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository <Book, Long>{

    List<Book> findByNameContainingIgnoreCase(String name);

    Page<Book> findByNameContainingIgnoreCase(String name, Pageable limit);

}