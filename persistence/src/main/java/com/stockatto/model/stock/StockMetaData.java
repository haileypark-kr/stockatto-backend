package com.stockatto.model.stock;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.stockatto.model.common.BaseEntity;
import com.stockatto.model.user.StockLiked;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "TBL_STOCK_META")
@ToString
public class StockMetaData extends BaseEntity {

	private static final long serialVersionUID = 5036260250868911024L;

	@Column(length = 255, nullable = false)
	private String name;

	@Column(nullable = false)
	private String code;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "STOCK_CUR_INFO_ID", foreignKey = @ForeignKey(name = "FK_STOCK_CUR_INFO_ID"))
	private StockCurInfo stockCurInfo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stockMeta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StockLiked> stockLikeds = new ArrayList<>();

	@Builder
	public StockMetaData(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public StockMetaData() {

	}
}
