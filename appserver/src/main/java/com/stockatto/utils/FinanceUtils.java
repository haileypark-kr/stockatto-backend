package com.stockatto.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockatto.converter.StockCurInfoConverter;
import com.stockatto.model.stock.StockCurInfo;

public class FinanceUtils {
	private static final String stockInfoReqUrl = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=";

	public static StockCurInfo getCurrentData(String code) throws IOException {
		Map<String, Object> info = FinanceUtils.getCurrentDataFromNaverFinance(code);
		StockCurInfoConverter converter = new StockCurInfoConverter();
		return converter.convert(info);
	}

	private static Map<String, Object> getCurrentDataFromNaverFinance(String code) throws IOException {
		String stockInfo = HttpRestUtils.restCallGet(stockInfoReqUrl + code);

		Map<String, Object> result = new ObjectMapper().readValue(stockInfo, HashMap.class);

		return result;
	}
}
