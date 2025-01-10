package com.salesapp.sales_app.controller;

import com.salesapp.sales_app.entity.Transaction;
import com.salesapp.sales_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Create a new transaction (Sale)
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.status(201).body(createdTransaction);
    }

    // Process a refund for a transaction
    @PostMapping("/refund/{transactionId}")
    public ResponseEntity<Transaction> processRefund(@PathVariable UUID transactionId, @RequestBody String updatedBy) {
        Transaction refundedTransaction = transactionService.processRefund(transactionId, updatedBy);
        return ResponseEntity.ok(refundedTransaction);
    }

    // Get all transactions within a specific date range (for reports)
    @GetMapping("/report")
    public ResponseEntity<List<Transaction>> getTransactionsReport(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        List<Transaction> transactions = transactionService.getTransactionsReport(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        return ResponseEntity.ok(transactions);
    }
}
