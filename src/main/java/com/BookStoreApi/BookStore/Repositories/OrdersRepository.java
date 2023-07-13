package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}