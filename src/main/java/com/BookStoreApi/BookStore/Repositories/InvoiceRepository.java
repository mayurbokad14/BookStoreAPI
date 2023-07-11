package com.BookStoreApi.BookStore.Repositories;

import com.BookStoreApi.BookStore.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}