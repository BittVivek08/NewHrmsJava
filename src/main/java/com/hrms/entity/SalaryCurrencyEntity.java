package com.hrms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "main_currency")
public class SalaryCurrencyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "currencyname")
	private String currencyName;

	@Column(name = "currencycode")
	private String currencyCode;

	@Column(name = "description")
	private String discription;

	@Column(name = "isactive")
	private int isActive;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "modifieddate")
	private Date modifiedDate;

}
