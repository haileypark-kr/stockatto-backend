package com.stockatto.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockatto.model.stock.StockCurInfo;

public interface StockCurInfoRepository extends JpaRepository<StockCurInfo, Long> {
}
