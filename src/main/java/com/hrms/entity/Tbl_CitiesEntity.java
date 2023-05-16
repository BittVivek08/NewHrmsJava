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
@Table(name = "tbl_cities")
public class Tbl_CitiesEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name = "state_id")
	private int stateId;

	@Column(name = "city_name")
	private String cityName;


	@Column(name = "is_active")
	private int isActive;

	
	@Column(name = "created")
	private Date createdDate;

	
	@Column(name = "modified")
	private Date modifiedDate;

	

}
