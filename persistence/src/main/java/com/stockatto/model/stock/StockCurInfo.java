package com.stockatto.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.stockatto.model.common.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "TBL_STOCK_CUR_INFO")
@ToString
public class StockCurInfo extends BaseEntity {

	private static final long serialVersionUID = -8762343975002442408L;

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "STOCK_META_ID", foreignKey = @ForeignKey(name = "FK_STOCK_META_ID"))
	// private StockMetaData stockMetaData;

	@Column
	private Long marketSum; // 시총

	@Column
	private Float per;

	@Column
	private Float eps;

	@Column
	private Float pbr;

	@Column
	private Float rate;

	@Column
	private Long now;

	@Column
	private Long diff;

	@Column
	private Long quant; // 거래량

	@Column
	private Long amount; // 거래 대금 (백만)

	@Column
	private Long high; // 당일 고가

	@Column
	private Long low; // 당일 저가

	@Builder
	public StockCurInfo(Long marketSum, Float per, Float eps, Float pbr, Float rate, Long now, Long diff,
		Long quant, Long amount, Long high, Long low) {
		this.marketSum = marketSum;
		this.per = per;
		this.eps = eps;
		this.pbr = pbr;
		this.rate = rate;
		this.now = now;
		this.diff = diff;
		this.quant = quant;
		this.amount = amount;
		this.high = high;
		this.low = low;
	}

	@Builder

	public StockCurInfo() {
	}
}
