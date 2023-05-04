package com.hrms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Businessunit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int bid;
	private String name;
	private String code;
	private String description;
	private String startdate;
	private String country;
	private String state;
	private String city;
	private String address1;
	private String address2;
	private String address3;

}
