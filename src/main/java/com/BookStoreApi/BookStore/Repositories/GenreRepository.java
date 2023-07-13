package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}