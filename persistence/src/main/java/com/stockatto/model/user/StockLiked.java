package com.stockatto.model.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stockatto.model.common.BaseEntity;
import com.stockatto.model.stock.StockMetaData;

import lombok.ToString;

@Entity
@Table(name = "TBL_STOCK_LIKED")
@ToString
public class StockLiked extends BaseEntity {
	private static final long serialVersionUID = 6623908531417715303L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ID"), nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_META_ID", foreignKey = @ForeignKey(name = "FK_STOCK_META_ID"), nullable = false)
	private StockMetaData stockMeta;

}
