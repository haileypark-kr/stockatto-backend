package com.stockatto.service.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockatto.converter.StockCurInfoConverter;
import com.stockatto.dto.stock.StockDto;
import com.stockatto.model.stock.StockCurInfo;
import com.stockatto.model.stock.StockMetaData;
import com.stockatto.repository.stock.StockMetaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {

	private final StockMetaRepository stockMetaRepository;

	public void createStock(StockDto stockDto) {
		StockMetaData stockMetaData = StockMetaData.builder().code(stockDto.getCode()).name(stockDto.getName()).build();

		try {
			stockMetaData.setStockCurInfo(this.getCurrentData(stockDto.getCode()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.stockMetaRepository.save(stockMetaData);
	}

	public StockCurInfo getCurrentData(String code) throws IOException {

		Map<String, Object> info = this.getCurrentStockPriceByRest(code);
		StockCurInfoConverter converter = new StockCurInfoConverter();
		return converter.convert(info);
	}

	private static final String stockInfoReqUrl = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=";

	private String restCallGet(String paramUrl) {
		String result = null;

		try {
			URL url = new URL(paramUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Data-Type", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			osw.flush();
			osw.close();

			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				result = sb.toString();
				br.close();
			}

			conn.disconnect();

		} catch (IOException e) {
		}

		return result;
	}

	public Map<String, Object> getCurrentStockPriceByRest(String code) throws IOException {
		String stockInfo = restCallGet(stockInfoReqUrl + code);

		Map<String, Object> result = new ObjectMapper().readValue(stockInfo, HashMap.class);

		return result;
	}
}
