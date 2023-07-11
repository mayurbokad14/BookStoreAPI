package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}