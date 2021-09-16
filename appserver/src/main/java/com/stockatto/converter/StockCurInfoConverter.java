package com.stockatto.converter;

import java.util.Map;

import org.springframework.core.convert.ConversionException;

import com.stockatto.model.stock.StockCurInfo;

public class StockCurInfoConverter extends AbstractDataConverter<Map<String, Object>, StockCurInfo> {
	@Override
	protected StockCurInfo createTarget() {
		return new StockCurInfo();
	}

	@Override
	public StockCurInfo convert(Map<String, Object> source, StockCurInfo target) throws ConversionException {
		target.setMarketSum(((Number)source.get("marketSum")).longValue());
		target.setPer(((Number)source.get("per")).floatValue());
		target.setEps(((Number)source.get("eps")).floatValue());
		target.setPbr(((Number)source.get("pbr")).floatValue());
		target.setNow(((Number)source.get("now")).longValue());
		target.setDiff(((Number)source.get("diff")).longValue());
		target.setRate(((Number)source.get("rate")).floatValue());
		target.setQuant(((Number)source.get("quant")).longValue());
		target.setAmount(((Number)source.get("amount")).longValue());
		target.setHigh(((Number)source.get("high")).longValue());
		target.setLow(((Number)source.get("low")).longValue());

		return target;
	}

	// target의 기존 데이터는 가만히 두고, 바뀐거만 업데이트
	public StockCurInfo convertRetainingOriginalTarget(Map<String, Object> source, StockCurInfo target) {
		return this.convert(source, target);
	}
}
