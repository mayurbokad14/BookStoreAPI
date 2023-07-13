package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}