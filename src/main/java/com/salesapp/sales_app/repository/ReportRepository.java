package com.salesapp.sales_app.repository;

import com.salesapp.sales_app.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    // Query untuk mendapatkan laporan berdasarkan rentang tanggal
    List<Report> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
