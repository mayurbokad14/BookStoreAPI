package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
}