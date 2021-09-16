package com.stockatto.repository.stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stockatto.model.stock.StockMetaData;

public interface StockMetaRepository extends JpaRepository<StockMetaData, Long> {

	@Query("select s from StockMetaData as s where s.code = ?1")
	Optional<StockMetaData> findByCode(String code);
}
