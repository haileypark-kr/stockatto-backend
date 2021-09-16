package com.stockatto.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockatto.model.user.StockLiked;

public interface StockLikedRepository extends JpaRepository<StockLiked, Long> {
}
