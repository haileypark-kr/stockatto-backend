package com.stockatto.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.stockatto.model.common.BaseEntity;

import lombok.ToString;

@Entity
@SequenceGenerator(name = "SeqGenerator", sequenceName = "SEQ_USER_ID", allocationSize = 1)
@Table(name = "TBL_USER")
@ToString
public class User extends BaseEntity {

	private static final long serialVersionUID = -954051936084315967L;

	@Column(length = 100, unique = true, nullable = false)
	private String userId;

	@Column(length = 50)
	private String name;

	@Column
	private String password;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<StockLiked> stockLikeds = new ArrayList<>();
}
