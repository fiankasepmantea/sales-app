package com.salesapp.sales_app.service;

import com.salesapp.sales_app.entity.Report;
import com.salesapp.sales_app.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    // Mendapatkan laporan transaksi dalam rentang tanggal
    public List<Report> getReport(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findByCreatedAtBetween(startDate, endDate);
    }
}
