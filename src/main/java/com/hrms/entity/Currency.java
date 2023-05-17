package com.hrms.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="main_currency")
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int currencyId;

	@Column(name = "currencyname")
	private String currencyName;

	private String currencycode;

	private String description;

	private int createdby;

	private int modifiedby;

	private LocalDateTime createddate;

	private LocalDateTime modifieddate;

	private Boolean isactive;
	
}
