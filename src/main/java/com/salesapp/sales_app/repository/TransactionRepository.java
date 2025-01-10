package com.salesapp.sales_app.repository;

import com.salesapp.sales_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // Query untuk mendapatkan transaksi berdasarkan rentang tanggal
    List<Transaction> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
