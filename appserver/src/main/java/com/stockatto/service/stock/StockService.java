package com.stockatto.service.stock;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stockatto.dto.stock.StockDto;
import com.stockatto.model.stock.StockCurInfo;
import com.stockatto.model.stock.StockMetaData;
import com.stockatto.repository.stock.StockCurInfoRepository;
import com.stockatto.repository.stock.StockMetaRepository;
import com.stockatto.utils.FinanceUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {

	private final StockMetaRepository stockMetaRepository;
	private final StockCurInfoRepository stockCurInfoRepository;

	public void createStock(StockDto stockDto) {
		Optional<StockMetaData> stockMetaDataSearchOptional = this.stockMetaRepository.findByCode(stockDto.getCode());

		StockCurInfo stockCurInfo = new StockCurInfo();
		try {
			stockCurInfo = FinanceUtils.getCurrentData(stockDto.getCode());
		} catch (IOException e) {
			//	exception raising
		}

		StockMetaData stockMetaData = null;
		if (stockMetaDataSearchOptional.isEmpty()) {
			stockMetaData = StockMetaData.builder()
				.code(stockDto.getCode())
				.name(stockDto.getName())
				.build();
		} else {
			stockMetaData = stockMetaDataSearchOptional.get();
		}

		stockCurInfo.setStockMetaData(stockMetaData);
		stockMetaData.setStockCurInfo(stockCurInfo);

		this.stockMetaRepository.save(stockMetaData);
	}

	public void updateStockCurInfo(StockDto stockDto) {
		Optional<StockMetaData> stockMetaDataSearchOptional = this.stockMetaRepository.findByCode(stockDto.getCode());

		if (stockMetaDataSearchOptional.isPresent()) {
			StockMetaData stockMetaDataSearch = stockMetaDataSearchOptional.get();

			StockCurInfo stockCurInfo = new StockCurInfo();
			try {
				stockCurInfo = FinanceUtils.getCurrentData(stockDto.getCode());

			} catch (IOException e) {
				//	exception raising

			}

			stockCurInfo.setStockMetaData(stockMetaDataSearch);
			stockMetaDataSearch.setStockCurInfo(stockCurInfo);

			this.stockMetaRepository.save(stockMetaDataSearch);
		} else {
			//	exception raising
		}
	}

}
