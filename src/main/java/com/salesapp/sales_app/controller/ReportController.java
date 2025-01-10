package com.salesapp.sales_app.controller;

import com.salesapp.sales_app.entity.Report;
import com.salesapp.sales_app.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Mendapatkan laporan transaksi berdasarkan rentang tanggal
    @GetMapping("/transactions")
    public ResponseEntity<List<Report>> getTransactionsReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        // Mendapatkan laporan transaksi dari service
        List<Report> reports = reportService.getReport(startDate, endDate);

        if (reports.isEmpty()) {
            return ResponseEntity.noContent().build(); // Mengembalikan 204 jika tidak ada laporan
        }

        // Mengembalikan laporan transaksi sebagai respons
        return ResponseEntity.ok(reports);
    }
}
