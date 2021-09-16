package com.stockatto.dto.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {

	private String code;
	private String name;

	@Builder
	public StockDto(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
