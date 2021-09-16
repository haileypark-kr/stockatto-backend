package com.stockatto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockatto.dto.stock.StockDto;
import com.stockatto.service.stock.StockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v0.1/stockatto/stock")
@RequiredArgsConstructor
public class StockController {

	private final StockService stockService;

	@PostMapping("/create")
	public String create(@RequestBody StockDto stockDto) {

		this.stockService.createStock(stockDto);
		
		return "Success";
	}

}
