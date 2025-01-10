package com.salesapp.sales_app.service;

import com.salesapp.sales_app.entity.Product;
import com.salesapp.sales_app.entity.Transaction;
import com.salesapp.sales_app.repository.ProductRepository;
import com.salesapp.sales_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new transaction (Sale)
    public Transaction createTransaction(Transaction transaction) {
        Product product = productRepository.findById(transaction.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < transaction.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        // Deduct stock
        product.setStock(product.getStock() - transaction.getQuantity());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);

        // Set transaction details
        transaction.setId(UUID.randomUUID());
        transaction.setTotalAmount(transaction.getQuantity() * product.getPrice());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    // Process a refund for a transaction
    public Transaction processRefund(UUID transactionId, String updatedBy) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Product product = productRepository.findById(transaction.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Return stock
        product.setStock(product.getStock() + transaction.getQuantity());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);

        // Mark transaction as refunded
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setUpdatedBy(updatedBy);
        transaction.setTotalAmount(0);  // Mark total amount as zero for refunds

        return transactionRepository.save(transaction);
    }

    // Get transactions report within a date range
    public List<Transaction> getTransactionsReport(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findByCreatedAtBetween(startDate, endDate);
    }
}
