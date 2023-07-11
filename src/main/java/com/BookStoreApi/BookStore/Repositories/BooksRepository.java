package com.BookStoreApi.BookStore.Repositories;

import java.util.List;

import com.BookStoreApi.BookStore.Models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository <Book, Long>{

    List<Book> findByTitleContainingIgnoreCase(String title);

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable limit);

}