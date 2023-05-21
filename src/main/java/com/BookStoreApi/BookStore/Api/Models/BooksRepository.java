package com.BookStoreApi.BookStore.Api.Models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository <Book, Long>{

}